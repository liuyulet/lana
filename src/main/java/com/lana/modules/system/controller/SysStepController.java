package com.lana.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.dto.StepNodeDTO;
import com.lana.modules.system.pojo.entity.SysStep;
import com.lana.modules.system.pojo.entity.SysStepNode;
import com.lana.modules.system.service.SysStepNodeService;
import com.lana.modules.system.service.SysStepService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

/**
 * (SysStep)表控制层
 *
 * @author liuyulet
 * @since 2022-10-22 14:31:42
 */
@Api(tags = "过程信息接口")
@ApiSupport(author = "liuyulet")
@RestController
@RequestMapping("/sysStep")
public class SysStepController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private SysStepService sysStepService;

    @Resource
    private SysStepNodeService sysStepNodeService;

    /**
     * 过程节点查询
     */
    @ApiOperation(value = "过程节点、人员信息查询", notes = "过程节点、人员信息查询")
    @GetMapping("/getstepNode")
    public Result getstep(@RequestParam Long stepId) {

        JSONObject dates = sysStepService.getByStep(stepId);

        return Result.ok(dates);
    }


    /**
     * 过程节点删除
     */
    @ApiOperation(value = "过程节点、人员信息删除", notes = "过程节点、人员信息删除")
    @GetMapping("/delstep")
    public Result delstep(@RequestParam Long stepId) {

        sysStepService.removeById(stepId);
        sysStepService.delStepNode(stepId);
        sysStepService.delStepNodeUser(stepId);
        return Result.ok();
    }


    /**
     * 获取过程节点信息列表
     */
    @ApiOperation(value = "协作过程列表", notes = "协作过程列表")
    @GetMapping("/getStep")
    public Result list(@RequestParam Map<String, Object> params) {

        PageUtils page = sysStepService.queryPage(params);

        return Result.ok(page);
    }


    /**
     * 保存过程节点
     */
    @ApiOperation(value = "保存过程节点用户信息", notes = "保存过程节点用户信息")
    @PostMapping("/saveStep")
    public Result saveStep(@RequestBody JSONObject jsonObject) {
        Result resuls = new Result();
        //检查是新增还是修改，type=1则为新增，type=0则为修改。修改只多了一步删除操作，删除完再重新添加；
        if (jsonObject.get("type").toString().equals("0")) {
            Result R = delstep(Long.parseLong(jsonObject.get("stepId").toString()));
            if (R.getCode() == 200) {
                System.out.println("删除成功，准备重新加入");
                resuls = stepAddUpdate(jsonObject);
            }
        } else {
            resuls = stepAddUpdate(jsonObject);
        }
        return resuls;
    }


    public Result stepAddUpdate(JSONObject jsonObject) {
        //处理数据保存过程信息
        SysStep sysStep = new SysStep();
        sysStep.setStepName(jsonObject.getString("stepName"));
        sysStep.setCreateTime(new Date());
        sysStep.setCreateUser(getUserName());
        sysStep.setStepNum(String.valueOf(jsonObject.getJSONArray("stepList").size()));
        sysStepService.saveSysStep(sysStep);
        Long stepId = sysStep.getStepId();
        //保存节点信息
        List<HashMap<String, Object>> stepList = jsonObject.getObject("stepList", List.class);
        List<SysStepNode> sysStepNodes = new ArrayList<>();
        //整理数据
        for (int i = 0; i < stepList.size(); i++) {
            SysStepNode sysStepNode = new SysStepNode();
            sysStepNode.setStepNodeLabel(stepList.get(i).get("label").toString());
            sysStepNode.setStepNodeValue(Long.valueOf(stepList.get(i).get("value").toString()));
            sysStepNode.setStepId(stepId);
            sysStepNodes.add(sysStepNode);
            //解析结果
            HashMap<String, Object> sss = jsonObject.getObject("userSelection", HashMap.class);
            //获取字符
            String userss = sss.get(stepList.get(i).get("value").toString()).toString();
            String value = userss.substring(1, userss.length() - 1);
            String dats = value.replace(" ", "");
            //生成数组
            String[] users = dats.split(",");
            int[] array = Arrays.stream(users).mapToInt(Integer::parseInt).toArray();
            sysStepService.savess(stepList.get(i).get("value").toString(), array, stepId);
        }
        sysStepNodeService.saveBatch(sysStepNodes);
        //保存节点关联用户信息
        //sysStepService.saveStepNodeUser(stepId,jsonObject.getJSONArray("userSelection"));
        return Result.ok();
    }


}

