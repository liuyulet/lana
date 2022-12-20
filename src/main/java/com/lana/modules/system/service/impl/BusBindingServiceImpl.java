package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.modules.system.dao.BusBindingDao;
import com.lana.modules.system.dao.SysDemanUserDao;
import com.lana.modules.system.dao.SysStepDao;
import com.lana.modules.system.pojo.entity.BusBindingEntity;
import com.lana.modules.system.pojo.entity.SysDemanUserEntity;
import com.lana.modules.system.service.BusBindingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 任务—流程绑定(BusBinding)表服务实现类
 *
 * @author lyl
 * @since 2022-12-07 21:26:49
 */
@Service("busBindingService")
public class BusBindingServiceImpl extends ServiceImpl<BusBindingDao, BusBindingEntity> implements BusBindingService {
    @Resource
    private SysStepDao sysStepDao;

    @Resource
    private BusBindingDao busBindingDao;

    @Resource
    private SysDemanUserDao sysDemanUserDao;

    //生成过程节点信息列表
    @Override
    public void startApproval(BusBindingEntity busBinding) {
        //获取该流程的节点信息、人员信息
        List<HashMap<String, Object>> stepPeople = sysStepDao.getByStepPeople(busBinding.getStepId());

        //根据人员以及节点信息，将人员代办信息加入到人员代办信息表中
        if (stepPeople.size() > 0) {
            //获取最小值
            int nodeMin = minData(stepPeople);
            //顶多三四个节点，这里为了赶进度循环处理数据库。后续优化
            for (int i = 0; i < stepPeople.size(); i++) {
                //节点信息
                String node = stepPeople.get(i).get("nodeId").toString();
                //人员信息
                //String[] users = stepPeople.get(i).get("userId").toString().split(",");
                List<String> nodeUser = Arrays.asList(stepPeople.get(i).get("userId").toString().split(","));
                //拉出该节点的人员信息
                List<HashMap<String, Object>> userMessge = busBindingDao.selUser(nodeUser);
                //将所有人员信息查询出来，依照人员id为主+人员名字+业务id+过程id+用户id+创建时间加入到人员意见表（oa_bus_user_opinion）
                //根据审批流id查询出对应的节点，根据节点信息查处对应的人员id，根据人员id查询出来对应的人员姓名
                if (userMessge.size() > 0) {
                    for (int j = 0; j < userMessge.size(); j++) {
                        //创建对象，入库
                        SysDemanUserEntity oaBusUserOpinion = new SysDemanUserEntity();
                        //如果是第一个节点的人员，则直接设置为应审，在代办中展示
                        if (nodeMin == Integer.parseInt(node)) {
                            //数据库默认为0，1:接受，2:驳回，3应做，0预置（刚分配）
                            oaBusUserOpinion.setUserOpinion(3);
                        }
                        //设置流程id
                        oaBusUserOpinion.setStepId(busBinding.getStepId());
                        oaBusUserOpinion.setNodeId(Long.parseLong(node));
                        oaBusUserOpinion.setUserId(Long.parseLong(userMessge.get(j).get("id").toString()));
                        oaBusUserOpinion.setUserName(userMessge.get(j).get("realname").toString());
                        oaBusUserOpinion.setPalnItemId(busBinding.getPalnItemId());
                        oaBusUserOpinion.setPalnItemName(busBinding.getPalnItemName());
                        oaBusUserOpinion.setPalnName(busBinding.getPalnName());
                        oaBusUserOpinion.setDataType(3);
                        oaBusUserOpinion.setCreateUser(busBinding.getCreateUser());
                        oaBusUserOpinion.setCreateTime(new Date());
                        sysDemanUserDao.insert(oaBusUserOpinion);
                    }
                }
            }
        }
    }

    @Override
    public void deletBinDing(Long itemDd) {
        //解除绑定的话则要删除之前分配好的内容
        //删除sys_deman_user表
        //删除绑定表信息
        sysDemanUserDao.deleteUserTask(itemDd);
        sysDemanUserDao.deleteBinding(itemDd);
    }

    public int minData(List<HashMap<String, Object>> stepPeople) {
        int minValue = Integer.parseInt(stepPeople.get(0).get("nodeId").toString());
        for (int i = 0; i < stepPeople.size(); i++) {
            if (Integer.parseInt(stepPeople.get(i).get("nodeId").toString()) < minValue) {
                minValue = Integer.parseInt(stepPeople.get(i).get("nodeId").toString());
            }
        }
        return minValue;
    }
}

