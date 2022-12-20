package com.lana.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Query;
import com.lana.modules.system.pojo.entity.SysDeman;
import com.lana.modules.system.pojo.entity.SysStep;
import com.lana.modules.system.dao.SysStepDao;
import com.lana.modules.system.service.SysStepService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysStep)表服务实现类
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:45
 */
@Service("sysStepService")
public class SysStepServiceImpl extends ServiceImpl<SysStepDao, SysStep> implements SysStepService {
    @Resource
    private SysStepDao sysStepDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<SysStep> page = this.page(
                new Query<SysStep>().getPage(params),
                new QueryWrapper<SysStep>()
                        .select().orderByDesc("create_time")
        );
        return new PageUtils(page);
    }

    @Override
    public void saveSysStep(SysStep sysStep) {
         sysStepDao.saveSysStep(sysStep);
    }

    @Override
    public void savess(String value, int[] users, Long stepId) {
        sysStepDao.savess(value,users,stepId);
    }

    @Override
    public JSONObject getByStep(Long stepId) {
        JSONObject jsonobject =new JSONObject();
        HashMap<Integer,int[]> maps =new HashMap<>();
        List<HashMap<String, Object>> stepNode = sysStepDao.getByStep(stepId);
        List<HashMap<String, Object>> stepPeople = sysStepDao.getByStepPeople(stepId);
        //根据过程id，查询出已有的节点和人员，进行数据处理完成之后将数据拼接为：{1:[1],2:[20],3[45]} 的格式，其中数组为人员id，key为过程节点id
        if(stepPeople.size()>0){
            for (int i = 0; i < stepPeople.size(); i++) {
                String[] users = stepPeople.get(i).get("userId").toString().split(",");
                int[] array = Arrays.stream(users).mapToInt(Integer::parseInt).toArray();
                maps.put(Integer.parseInt(stepPeople.get(i).get("nodeId").toString()),array);
            }
        }
        jsonobject.put("stepNode",stepNode);
        jsonobject.put("stepPeople",maps);
        return jsonobject;
    }

    @Override
    public void delStepNode(Long stepId) {
        sysStepDao.delStepNode(stepId);
    }

    @Override
    public void delStepNodeUser(Long stepId) {
        sysStepDao.delStepNodeUser(stepId);
    }

    @Override
    public List<Long> getUserId(Object stepId, Object stepNode) {
        return sysStepDao.getUserId(stepId,stepNode);
    }

}
