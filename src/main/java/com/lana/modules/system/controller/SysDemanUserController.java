package com.lana.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.modules.system.pojo.dto.OverTeskDTO;
import com.lana.modules.system.pojo.entity.PalnItemEntity;
import com.lana.modules.system.pojo.entity.SysDemanUserEntity;
import com.lana.modules.system.pojo.entity.SysDepart;
import com.lana.modules.system.pojo.vo.SysDemanUserVO;
import com.lana.modules.system.service.PalnItemService;
import com.lana.modules.system.service.SysDemanUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (SysDemanUser)表控制层
 * @auther liuyulet
 * @since 2022-10-07 23:16:34
 */
@Api(tags = "任务人员分配")
@ApiSupport(author = "liuyulet")
@RestController
@RequestMapping("/sysDemanUser")
public class SysDemanUserController extends AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysDemanUserService sysDemanUserService;
    @Resource
    private PalnItemService palnItemService;

    /**
     * 所有需求列表
     */
    @ApiOperation(value = "任务列表", notes = "任务列表")
    @GetMapping("/getTesk")
    public Result list(@RequestParam Map<String, String> params) {

        PageUtils page = sysDemanUserService.queryPage(params,getUser());

        return Result.ok(page);
    }


    /**
     * 修改
     *
     * @return 修改
     *
     */
    @ApiOperation(value = "开始任务", notes = "开始任务")
    @GetMapping("/updateTesk")
    public Result updateDepart(@RequestParam String taskId) {

        SysDemanUserEntity sysDemanUserEntity = sysDemanUserService.getById(taskId);
        sysDemanUserEntity.setUserOpinion(1);
        sysDemanUserEntity.setUpdataTime(new Date());
        sysDemanUserService.updateById(sysDemanUserEntity);
        //一旦开始了，那么计划控制管理中的任务绑定信息将无法撤回。

        PalnItemEntity palnItemEntity = palnItemService.getById(sysDemanUserEntity.getPalnItemId());
        palnItemEntity.setPlanStatus(1);
        palnItemService.updateById(palnItemEntity);
        return Result.ok();
    }



    /**
     * 任务进度查看
     * @return 任务进度查看
     */
    @ApiOperation(value = "我得任务进度查看", notes = "我得任务进度查看")
    @GetMapping("/findByStatus")
    public Result findByStatus(
            @RequestParam(name="itemid") String itemid
    ) {
        SysDemanUserEntity sysDemanUserEntity = sysDemanUserService.getById(itemid);
        //代办信息列表
        List<SysDemanUserEntity>  sysDemanUserEntitys = sysDemanUserService.selectdata(sysDemanUserEntity.getPalnItemId());
        //将所有的节点信息处理
        List<Long> nodeList = new ArrayList<>();
        for (int i = 0; i < sysDemanUserEntitys.size(); i++) {
            nodeList.add(sysDemanUserEntitys.get(i).getNodeId());
        }
        List<SysDemanUserVO> sysDemanUserEntityvo = new ArrayList<>();
        //去重相关的节点
        List<Long> duplicateNode = nodeList.stream().distinct().collect(Collectors.toList());
        //sysDemanUserService.updateById(sysDemanUserEntity);

        for (int i = 0; i < duplicateNode.size(); i++) {
            //同一个节点下有几个要审批的用户
            List<SysDemanUserEntity> sysDemanUser = new ArrayList<>();
            for (int j = 0; j < sysDemanUserEntitys.size(); j++) {
                if(duplicateNode.get(i)==sysDemanUserEntitys.get(j).getNodeId()){
                    sysDemanUser.add(sysDemanUserEntitys.get(j));
                }
            }
            //拼接数据
            SysDemanUserVO demanUserVO= new SysDemanUserVO();
            demanUserVO.setNodeId(duplicateNode.get(i));
            demanUserVO.setSysDemanUserEntity(sysDemanUser);
            sysDemanUserEntityvo.add(demanUserVO);
        }


        return Result.ok(sysDemanUserEntityvo);
    }



    /**
     * 任务进度查看
     * @return 任务进度查看
     */
    @ApiOperation(value = "任务维护查看任务进度查看", notes = "任务维护查看任务进度查看")
    @GetMapping("/taskByStatus")
    public Result taskByStatus(
            @RequestParam(name="itemid") Long itemid
    ) {
        //代办信息列表
        List<SysDemanUserEntity>  sysDemanUserEntitys = sysDemanUserService.selectdata(itemid);
        //将所有的节点信息处理
        List<Long> nodeList = new ArrayList<>();
        for (int i = 0; i < sysDemanUserEntitys.size(); i++) {
            nodeList.add(sysDemanUserEntitys.get(i).getNodeId());
        }
        List<SysDemanUserVO> sysDemanUserEntityvo = new ArrayList<>();
        //去重相关的节点
        List<Long> duplicateNode = nodeList.stream().distinct().collect(Collectors.toList());
        //sysDemanUserService.updateById(sysDemanUserEntity);

        for (int i = 0; i < duplicateNode.size(); i++) {
            //同一个节点下有几个要审批的用户
            List<SysDemanUserEntity> sysDemanUser = new ArrayList<>();
            for (int j = 0; j < sysDemanUserEntitys.size(); j++) {
                if(duplicateNode.get(i)==sysDemanUserEntitys.get(j).getNodeId()){
                    sysDemanUser.add(sysDemanUserEntitys.get(j));
                }
            }
            //拼接数据
            SysDemanUserVO demanUserVO= new SysDemanUserVO();
            demanUserVO.setNodeId(duplicateNode.get(i));
            demanUserVO.setSysDemanUserEntity(sysDemanUser);
            sysDemanUserEntityvo.add(demanUserVO);
        }


        return Result.ok(sysDemanUserEntityvo);
    }



    /**
     * 检验
     * @return 检验
     */
    @ApiOperation(value = "校验完成度", notes = "校验完成度")
    @GetMapping("/getAllOver")
    public Result getAllOver(@RequestParam String demanId) {

        List<HashMap<String,Object>> datas = sysDemanUserService.getAllOver(demanId);
        if(datas.size()>0){
            return Result.error("还有未完成的协作者，无法进行下一步");

        }else {
            return Result.ok();
        }
    }


    /**
     * 检验
     * @return 检验
     */
    @ApiOperation(value = "完成任务/驳回任务", notes = "完成任务/驳回任务")
    @PostMapping("/overTask")
    public Result overTask(@RequestBody OverTeskDTO overTeskDTO) {

        Result datas = sysDemanUserService.overTask(overTeskDTO);

        return datas;
    }

}

