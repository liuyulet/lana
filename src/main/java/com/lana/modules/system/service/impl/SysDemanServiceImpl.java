package com.lana.modules.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.dao.SysDemanDao;
import com.lana.modules.system.pojo.dto.NextForDemdDTO;
import com.lana.modules.system.pojo.dto.UserForDemdDTO;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.pojo.vo.SysDemanVO;
import com.lana.modules.system.pojo.vo.TaskUserDataVO;
import com.lana.modules.system.service.SysDemanService;
import com.lana.modules.system.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * (SysDeman)表服务实现类
 *
 * @author liuyulet
 * @since 2022-09-30 11:25:42
 */
@Service("sysDemanService")
public class SysDemanServiceImpl extends ServiceImpl<SysDemanDao, SysDeman> implements SysDemanService {

    @Autowired
    private SysDemanDao sysDemanDao;

    @Override
    public PageUtils queryPage(Map<String, String> params,SysUserEntity userEntit) {
        Page<HashMap<String,Object>> page = new Page<>(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("limit").toString()));
        IPage<SysDemanVO> pages = sysDemanDao.getPageData(page,userEntit,params);
        return new PageUtils(page);
    }

    @Override
    public void userForDeman(UserForDemdDTO userForDemdDTO) {
        //stream循环插入数据
        if(userForDemdDTO.getUserId().length>0){
            int[] users= userForDemdDTO.getUserId();
            List<Integer> userList= Arrays.stream(users).boxed().collect(Collectors.toList());
            sysDemanDao.userForDeman(userForDemdDTO.getDemendId(),userList,userForDemdDTO.getUserName());
        }
    }

    @Override
    public PageUtils getMyDeman(Map<String, Object> params) {

        String userid = params.get("userId").toString();

        IPage<SysDeman> page = this.page(
                new Query<SysDeman>().getPage(params),
                new QueryWrapper<SysDeman>()
                        .select().orderByDesc("create_time")
        );
        return new PageUtils(page);
    }

    @Override
    public void nextForDeman(NextForDemdDTO nextForDemdDTO) {
        //stream循环插入数据
        if(nextForDemdDTO.getUserId().length>0){
            int[] users= nextForDemdDTO.getUserId();
            List<Integer> userList= Arrays.stream(users).boxed().collect(Collectors.toList());
            sysDemanDao.userForDeman(nextForDemdDTO.getDemendId(),userList,nextForDemdDTO.getUserName());
        }
    }
}
