package com.lana.modules.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.modules.system.dao.SysDepartDao;
import com.lana.modules.system.pojo.dto.UserForDepartDTO;
import com.lana.modules.system.pojo.entity.SysDepart;
import com.lana.modules.system.service.SysDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 组织机构表(SysDepart)表服务实现类
 *
 * @author liuyulet
 * @since 2022-09-29 10:02:49
 */
@Service("sysDepartService")
public class SysDepartServiceImpl extends ServiceImpl<SysDepartDao, SysDepart> implements SysDepartService {
    @Autowired
    private SysDepartDao sysDepartDao;

    @Override
    public void userForDepart(UserForDepartDTO userForDepartDTO) {
        //stream循环插入数据
        if(userForDepartDTO.getUserId().length>0){
            int[] users= userForDepartDTO.getUserId();
            List<Integer> userList= Arrays.stream(users).boxed().collect(Collectors.toList());
            sysDepartDao.userForDepart(userForDepartDTO.getDepartId(),userList);
        }
        if (userForDepartDTO.getUserUpdateId().length>0) {
            int[] updatUsers= userForDepartDTO.getUserUpdateId();
            List<Integer> userUpdateList= Arrays.stream(updatUsers).boxed().collect(Collectors.toList());
            sysDepartDao.userUpdateForDepart(userForDepartDTO.getDepartId(),userUpdateList);
        }
    }
}

