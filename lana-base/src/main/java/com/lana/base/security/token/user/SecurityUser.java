package com.lana.base.security.token.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**用户
 * @auther liuyulet
 * @date 2024/3/16 14:37
 */
public class SecurityUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUser.class);
    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail user;
        try {
            user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            //LOGGER.error("Failed to get user details from SecurityContextHolder", e);
            return null;
        }
        return user;
    }
    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        UserDetail user = getUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

}
