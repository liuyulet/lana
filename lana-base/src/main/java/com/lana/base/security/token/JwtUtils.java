package com.lana.base.security.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @auther liuyulet
 * @date 2024/3/26 11:28
 */
@Data
@Component
public class JwtUtils {

    @Value("${lana.security.secretKey}")
    private String secretKey;//密钥
    @Value("${lana.security.access-token-expire}")
    private long accessTokenExpire;//密钥
    @Value("${lana.security.refresh-token-expire}")
    private long refreshTokenExpire;//密钥

    /**
     * 从Token中获取Username
     * @param token Token
     * @return String
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从Token中获取UserId
     * @param token Token
     * @return String
     */
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getId);
    }

    /**
     * 从Token中获取数据,根据传入不同的Function返回不同的数据
     * eg: String extractUsername(String token)
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 生成Token无额外信息
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * 生成Token,有额外信息
     * @param extraClaims 额外的数据
     * @param userDetails 用户信息
     * @return String
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, accessTokenExpire);
    }

    /**
     * 生成刷新用的Token
     * @param userDetails 用户信息
     * @return String
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshTokenExpire);
    }


    /**
     * 验证Token是否有效
     * @param token Token
     * @param userDetails 用户信息
     * @return boolean
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * 判断Token是否过去
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 从Token中获取失效时间
     */
    private Date extractExpiration(String token) {
        //通用方法,传入一个Function,返回一个T
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 从Token中获取所有数据
     */
    private Claims extractAllClaims(String token) {
        Claims claims;
        try{
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e){
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 获取签名Key
     * Token 加密解密使用
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    /**
     * 构建Token方法
     * @param extraClaims 额外信息
     * @param userDetails //用户信息
     * @param expiration //失效时间
     * @return String
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                //body
                .setSubject(userDetails.getUsername())
                //主题数据
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //设置发布时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                //设置过期时间
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                //设置摘要算法
                .compact();
    }

}
