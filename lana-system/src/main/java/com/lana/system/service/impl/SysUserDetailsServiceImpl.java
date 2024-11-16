package com.lana.system.service.impl;

import com.lana.base.security.token.user.UserDetail;
import com.lana.system.dao.SysRoleDao;
import com.lana.base.syshandle.enums.DataScopeEnum;
import com.lana.base.syshandle.enums.UserStatusEnum;
import com.lana.system.service.SysMenusService;
import com.lana.system.service.SysOrgService;
import com.lana.system.service.SysUserDetailsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @auther liuyulet
 * @date 2024/3/19 13:34
 */
@Slf4j
@Service
public class SysUserDetailsServiceImpl implements SysUserDetailsService {
    @Resource
    private SysMenusService sysMenusService;
    @Resource
    private SysOrgService sysOrgService;
    @Resource
    private SysRoleDao sysRoleDao;


    /**
     * 获取用户详细信息，包括用户的数据权限和角色权限。
     *
     * @param userDetail 包含用户基本信息的对象，如用户ID、状态等。
     * @return 返回更新后的用户详细信息，包括数据权限范围和角色权限列表。
     */
    @Override
    public UserDetails getUserDetails(UserDetail userDetail) {
        // 检查用户状态，若为禁用，则设置enabled为false
        if (userDetail.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userDetail.setEnabled(false);
        }


        //处理用用户的接口权限数据
        Set<String> authoritySet = sysMenusService.getUserAuthority(userDetail);
        //权限信息，即接口上的@PreAuthorize注解
        userDetail.setAuthoritySet(authoritySet);


        // 获取并设置用户所属角色中所拥有的数据权限范围
        List<Long> dataScopeList = getDataScope(userDetail);
        userDetail.setDataScopeList(dataScopeList);


        return userDetail;
    }


    /**
     * 根据用户详情获取数据权限范围。
     *
     * @param userDetail 用户详细信息，包含用户ID和组织ID等。
     * @return 返回数据权限范围的ID列表。如果数据权限为全部，则返回null；如果是自定义范围，则返回自定义范围的ID列表。
     */
    private List<Long> getDataScope(UserDetail userDetail) {

        /* 对照前端的列表
        <el-option label="全部可见" value="0"></el-option>
        <el-option label="所在部门及子级可见" value="1"></el-option>
        <el-option label="所在部门可见" value="2"></el-option>
        <el-option label="本人可见" value="3"></el-option>
        */

        // 以最大角色权限为主
        Integer dataScope = sysRoleDao.getDataScopeByUserId(userDetail.getId());
        if (dataScope == null) {
            // 如果没有配置数据权限，则返回空列表
            return new ArrayList<>();
        }
        if (dataScope == (DataScopeEnum.ALL.getValue())) {
            // 如果数据权限为全部，则返回null
            return null;

        } else if (dataScope == (DataScopeEnum.ORGALL.getValue())) {
            // todo 备注，这里是根据用户信息进行进行查询的，也决定了这个的用户的上限。后期可以考虑走orgid路线
            // 如果数据权限为所在部门及子级可见，则获取本机构及子机构ID列表,并将组织下的用户信息加入到集合中
            List<Long> dataScopeList = sysOrgService.getOrgIdList(userDetail.getId());
            return dataScopeList;


        } else if (dataScope == (DataScopeEnum.ORGONLY.getValue())) {
            // 如果数据权限仅为本机构，则只包含本机构ID
            List<Long> dataScopeList = sysOrgService.getOnlyOrgIdList(userDetail.getId());
            return dataScopeList;

        }
        else if (dataScope == (DataScopeEnum.MYSELF.getValue())) {
            // 如果数据权限为自己可见
            List<Long> dataScopeList = new ArrayList<>();
            dataScopeList.add(userDetail.getId());
            dataScopeList.add(-1L);
        }

        return new ArrayList<>();
    }

}
