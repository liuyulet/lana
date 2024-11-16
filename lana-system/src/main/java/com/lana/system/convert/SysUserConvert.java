package com.lana.system.convert;

import com.lana.base.security.token.user.UserDetail;
import com.lana.system.entity.SysUserEntity;
import com.lana.system.entity.vo.result.SysUserResult;
import com.lana.system.entity.vo.save.SysUserSave;
import com.lana.system.entity.vo.update.SysUserUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserResult convert(SysUserEntity entity);

    SysUserEntity convert(SysUserResult vo);

    SysUserEntity convert(SysUserSave save);

    SysUserEntity convert(SysUserUpdate update);

    SysUserResult convert(UserDetail userDetail);

    UserDetail convertDetail(SysUserEntity entity);

    List<SysUserResult> convertList(List<SysUserEntity> list);

}
