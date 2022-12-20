
package com.lana.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.auth.TokenGenerator;
import com.lana.common.utils.Result;
import com.lana.modules.system.dao.SysUserTokenDao;
import com.lana.modules.system.pojo.entity.SysUserTokenEntity;
import com.lana.modules.system.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;


	@Override
	public Result createToken(long userId) {
		Result<JSONObject> result = new Result<JSONObject>();
		String token = TokenGenerator.generateValue();
		Date now = new Date();
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		SysUserTokenEntity tokenEntity = this.getById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			//保存token
			this.save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			this.updateById(tokenEntity);
		}
		JSONObject res = new JSONObject();
		res.put("token", token);
		res.put("expire", EXPIRE);
		Result results = Result.ok(res);

		return results;
	}

	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}
}
