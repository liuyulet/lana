package com.lana.system.service;

import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.BaseService;
import com.lana.system.entity.SysUserEntity;
import com.lana.system.entity.vo.query.SysUserQuery;
import com.lana.system.entity.vo.result.SysUserResult;
import com.lana.system.entity.vo.save.SysUserSave;
import com.lana.system.entity.vo.update.SysUserUpdate;
import com.lana.system.entity.vo.update.SysUserUpdatePassword;

import java.util.List;

public interface SysUserService extends BaseService<SysUserEntity> {

    LanaPage<SysUserResult> page(SysUserQuery query);

    void delete(List<Long> idList);

    void saveUserOrgRole(SysUserSave vo);

    void updateByUserId(SysUserUpdate vo);

    SysUserEntity getByIdUser(Long id);

    SysUserResult updateMySelf(SysUserUpdate sysUserUpdate);

    String updateMySelfPassword(SysUserUpdatePassword sysUserUpdatePassword);

    void registerUser(SysUserSave vo);
}
