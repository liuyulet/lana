package com.lana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lana.base.syshandle.exception.LanaException;
import com.lana.base.syshandle.page.LanaPage;
import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.system.convert.SysUserConvert;
import com.lana.system.dao.SysUserDao;
import com.lana.system.entity.SysUserEntity;
import com.lana.system.entity.vo.query.SysUserQuery;
import com.lana.system.entity.vo.result.SysUserCopyResult;
import com.lana.system.entity.vo.result.SysUserResult;
import com.lana.system.entity.vo.save.SysUserSave;
import com.lana.system.entity.vo.update.SysUserUpdate;
import com.lana.system.entity.vo.update.SysUserUpdatePassword;
import com.lana.system.service.SysUserOrgService;
import com.lana.system.service.SysUserRoleService;
import com.lana.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther liuyulet
 * @date 2024/3/20 18:02
 */
@Slf4j
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysUserOrgService sysUserOrgService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public LanaPage<SysUserResult> page(SysUserQuery query) {
        IPage<SysUserCopyResult> page = baseMapper.getLists(getPage(query),query,true);

        List<SysUserResult> resultList = page.getRecords().stream()
                .map(dto -> {
                    SysUserResult result = new SysUserResult();
                    // 复制基本属性...
                    result.setId(dto.getId());
                    result.setUsername(dto.getUsername());
                    result.setRealName(dto.getRealName());
                    result.setAvatar(dto.getAvatar());
                    result.setEmail(dto.getEmail());
                    result.setMobile(dto.getMobile());
                    result.setGender(dto.getGender());
                    result.setStatus(dto.getStatus());
                    result.setSuperAdmin(dto.getSuperAdmin());
                    result.setCreateTime(dto.getCreateTime());
                    result.setPostIdList(StringUtils.isNotBlank(dto.getPostIdList())
                            ? Arrays.stream(dto.getPostIdList().split(","))
                            .map(Long::valueOf)
                            .collect(Collectors.toList())
                            : Collections.emptyList());
                /*    if(dto.getPostIdList()!=null){
                        result.setPostId(Long.parseLong(dto.getPostIdList()));
                    }*/
                    result.setRoleIdList(StringUtils.isNotBlank(dto.getRoleIdList())
                            ? Arrays.stream(dto.getRoleIdList().split(","))
                            .map(Long::valueOf)
                            .collect(Collectors.toList())
                            : Collections.emptyList());

                    return result;
                })
                .collect(Collectors.toList());
        return new LanaPage<>(resultList, page.getTotal(),page.getPages(),page.getSize());
    }


    /**
     * 新增用户
     * @param vo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserOrgRole(SysUserSave vo) {
        try {
            //实体转换
            SysUserEntity user = SysUserConvert.INSTANCE.convert(vo);
            //保存用户
            baseMapper.insert(user);
            //保存角色
            List<Long> userRole = vo.getGroup();
            sysUserRoleService.saveUserRole(user.getId(),userRole);
            //保存组织
            List<Long> userOrg = vo.getDept();
            sysUserOrgService.saveUserOrg(user.getId(),userOrg);

        }catch (Exception e){
            log.info("保存用户、维护用户组织、角色出现异常，请排查",e);
            throw new LanaException("保存用户、维护用户组织、角色出现异常，请排查");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByUserId(SysUserUpdate vo) {
        try {
            //实体转换
            SysUserEntity user = SysUserConvert.INSTANCE.convert(vo);
            //修改用户
            baseMapper.updateById(user);
            //修改角色
            List<Long> userRole = vo.getGroup();
            sysUserRoleService.deleteByUserIdList(user.getId());
            sysUserRoleService.saveUserRole(user.getId(),userRole);
            //修改组织
            List<Long> userOrg = vo.getDept();
            sysUserOrgService.deleteByUserIdList(user.getId());
            sysUserOrgService.saveUserOrg(user.getId(),userOrg);

        }catch (Exception e){
            log.info("保存用户、维护用户组织、角色出现异常，请排查",e);
            throw new LanaException("保存用户、维护用户组织、角色出现异常，请排查");
        }
    }

    @Override
    public SysUserEntity getByIdUser(Long id) {

        return getById(id);
    }

    @Override
    public SysUserResult updateMySelf(SysUserUpdate sysUserUpdate) {
        SysUserEntity sysUserEntity = baseMapper.getByUsername(sysUserUpdate.getUsername());
        sysUserEntity.setSignature(sysUserUpdate.getSignature());
        sysUserEntity.setRealName(sysUserUpdate.getRealName());
        sysUserEntity.setGender(sysUserUpdate.getGender());
        baseMapper.updateById(sysUserEntity);
        return null;
    }

    @Override
    public String updateMySelfPassword(SysUserUpdatePassword sysUserUpdatePassword) {
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sysUserUpdatePassword.getUsername(), sysUserUpdatePassword.getPassword()));
        } catch (BadCredentialsException e) {
            throw new LanaException("原始密码错误，请重新输入");
        }
        if (authentication != null) {
            // 获取用户信息
            SysUserEntity sysUserEntity = baseMapper.getByUsername(sysUserUpdatePassword.getUsername());
            sysUserEntity.setPassword(passwordEncoder.encode(sysUserUpdatePassword.getNewPassword()));
            baseMapper.updateById(sysUserEntity);
        }
        return null;
    }

    @Override
    public void registerUser(SysUserSave vo) {
        //实体转换
        SysUserEntity user = SysUserConvert.INSTANCE.convert(vo);
        //保存用户
        baseMapper.insert(user);
    }


    /**
     * 删除用户
     * @param idList
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        removeByIds(idList);

        // 删除用户角色关系
        sysUserRoleService.deleteByUserIdList(idList);

        // 删除用户组织关系
        sysUserOrgService.deleteByUserIdList(idList);
    }


/*    @Override
    public PageResult<SysUserResult> page(SysUserQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<SysUserEntity> page = getPage(query);
        params.put(SysConstant.PAGE, page);
        // 数据列表
        List<SysUserEntity> list = baseMapper.getList(params);

        return new PageResult<>(SysUserConvertBasic.INSTANCE.convertList(list), page.getTotal());
    }

    private Map<String, Object> getParams(SysUserQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", query.getUsername());
        params.put("mobile", query.getMobile());
        params.put("gender", query.getGender());

        // 数据权限
        params.put(SysConstant.DATA_SCOPE, getDataScope("t1", null));

        return params;
    }*/

}
