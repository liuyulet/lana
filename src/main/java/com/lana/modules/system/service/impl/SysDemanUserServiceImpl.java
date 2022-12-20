package com.lana.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.common.utils.Result;
import com.lana.modules.system.dao.PalnItemDao;
import com.lana.modules.system.dao.SysDemanUserDao;
import com.lana.modules.system.dao.SysStepDao;
import com.lana.modules.system.pojo.dto.OverTeskDTO;
import com.lana.modules.system.pojo.entity.PalnItemEntity;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysDemanUserEntity;

import com.lana.modules.system.pojo.entity.SysUserEntity;
import com.lana.modules.system.pojo.vo.TaskUserDataVO;
import com.lana.modules.system.service.SysDemanUserService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysDemanUser)表服务实现类
 * @auther liuyulet
 * @since 2022-10-07 23:16:36
 */
@Service("sysDemanUserService")
public class SysDemanUserServiceImpl extends ServiceImpl<SysDemanUserDao, SysDemanUserEntity> implements SysDemanUserService {
    @Resource
    private SysDemanUserDao sysDemanUserDao;
    @Resource
    private SysStepDao sysStepDao;
    @Resource
    private PalnItemDao palnItemDao;

    @Override
    public List<HashMap<String, Object>> getAllOver(String demanId) {
        return sysDemanUserDao.getAllOver(demanId);
    }

    @Override
    public PageUtils queryPage(Map<String, String> params, SysUserEntity userEntit) {
        Page<HashMap<String,Object>> page = new Page<>(Integer.parseInt(params.get("page")), Integer.parseInt(params.get("limit")));
        IPage<TaskUserDataVO> pages = sysDemanUserDao.getPageData(page,userEntit,params);
        return new PageUtils(pages);
    }

    @Override
    public List<SysDemanUserEntity> selectdata(Long palnItemId) {
        return sysDemanUserDao.selectdata(palnItemId);
    }

    @Override
    public Result overTask(OverTeskDTO overTeskDTO) {
        SysDemanUserEntity sysDemanUserEntity = sysDemanUserDao.selectById(overTeskDTO.getDemanId());
        //获取该流程的节点信息、人员信息
        List<HashMap<String, Object>> stepPeople = sysStepDao.getByStepPeople(sysDemanUserEntity.getStepId());
        //完成
        if (overTeskDTO.getTaskType() == 1) {
            //修改当前人员的完成状态，
            sysDemanUserEntity.setUserOpinion(4);
            sysDemanUserEntity.setUserRemarks(overTeskDTO.getContents());
            sysDemanUserDao.updateById(sysDemanUserEntity);
            //查询出下一个节点数据（直接置为代办）
            int nodeNext = nodeNext(stepPeople, sysDemanUserEntity.getNodeId());
            //确定流程走完，
            if (nodeNext == sysDemanUserEntity.getNodeId() + 15) {
                //将所有审批人员状态数据置为审批通过
                sysDemanUserDao.overUpdate(sysDemanUserEntity.getPalnItemId(),sysDemanUserEntity.getStepId());
                //更新任务状态，用于计划列表中的状态展示
                PalnItemEntity palnItemEntity = palnItemDao.selectById(sysDemanUserEntity.getPalnItemId());
                palnItemEntity.setPlanStatus(2);
                palnItemDao.updateById(palnItemEntity);
                //审批走完且通过，加入到消息提醒中；
                //handService(OaApplyTypeEnum.pass.getCode(), oaBusUserOpinion);
            } else {
                //将下一个节点的人员信息全部搞上，置为待办
                sysDemanUserDao.nextUpdate(nodeNext, sysDemanUserEntity.getStepId(), sysDemanUserEntity.getPalnItemId());
            }
            //驳回
        } else if (overTeskDTO.getTaskType() == 0) {

            //修改当前人员的完成状态，
            sysDemanUserEntity.setUserOpinion(2);
            sysDemanUserEntity.setUserRemarks(overTeskDTO.getContents());
            sysDemanUserDao.updateById(sysDemanUserEntity);

            //更新该任务下的所有审批状态为驳回
            sysDemanUserDao.backUpdate(sysDemanUserEntity.getPalnItemId(),sysDemanUserEntity.getStepId());

            //修改整条任务信息的状态
            PalnItemEntity palnItemEntity = palnItemDao.selectById(sysDemanUserEntity.getPalnItemId());
            palnItemEntity.setPlanStatus(3);
            palnItemDao.updateById(palnItemEntity);

            //审批被驳回，加入消息；
            //handService(OaApplyTypeEnum.reject.getCode(), oaBusUserOpinion);
        }
        return Result.ok();
    }

    //判断下一节点信息，和是否已经完成
    public int nodeNext(List<HashMap<String, Object>> stepPeople, Long nownode) {

        //默认最多14个节点
        int nextNode = nownode.intValue();
        for (int k = 0; k < 15; k++) {
            //挨个节点查找，找到对应的下一个节点信息
            nextNode++;
            for (int i = 0; i < stepPeople.size(); i++) {
                if (Integer.parseInt(stepPeople.get(i).get("nodeId").toString()) == nextNode) {
                    return nextNode;
                }
            }
        }
        return nextNode;
    }
}
