package com.lana.modules.system.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.lana.common.exception.LException;
import com.lana.common.utils.MinioUtils;
import com.lana.common.utils.PageUtils;
import com.lana.common.utils.Result;
import com.lana.config.MinioConfig;
import com.lana.modules.system.pojo.entity.SysProjectEntity;
import com.lana.modules.system.service.SysProjectService;
import io.minio.ObjectWriteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * (SysProject)表控制层
 *
 * @auther liuyulet
 * @since 2022-10-06 11:23:37
 */
@RestController
@Api(tags = "项目管理")
@ApiSupport(author = "liuyulet")
@RequestMapping("/sysProject")
public class SysProjectController  extends  AbstractController{
    /**
     * 服务对象
     */
    @Resource
    private SysProjectService sysProjectService;

    /**
     * 项目
     * @return 新增
     */
    @ApiOperation(value = "新增项目", notes = "新增项目")
    @PostMapping("/addProject")
    public Result addProject(@RequestBody SysProjectEntity sysProjectEntity) {
        sysProjectEntity.setCreateTime(new Date());
        sysProjectService.save(sysProjectEntity);

        return Result.ok();
    }


    /**
     * 项目
     * @return 查询结果
     */
    @ApiOperation(value = "项目列表", notes = "项目列表 不用传任何参数")
    @GetMapping("/getProject")
    public Result getProject(@RequestParam Map<String, Object> params) {

        PageUtils page = sysProjectService.queryPage(params);

        return Result.ok(page);
    }

    /**
     * 项目
     * @return 查询结果
     */
    @ApiOperation(value = "查询全部项目", notes = "查询全部项目")
    @GetMapping("/getProjectAll")
    public Result getProjectAll(@RequestParam Map<String, Object> params) {
        Result<JSONObject> result = new Result<JSONObject>();
        JSONObject res = new JSONObject();
        List<SysProjectEntity> page = sysProjectService.list();
        res.put("projectData",page);
        result.setCode(200);
        result.setResult(res);
        return result;
    }

    /**
     * 项目
     * @return 修改
     */
    @ApiOperation(value = "修改项目", notes = "修改项目")
    @PostMapping("/updateProject")
    public Result updateProject(@RequestBody SysProjectEntity sysProjectEntity) {

        sysProjectService.updateById(sysProjectEntity);

        return Result.ok();
    }

    /**
     * 删除
     * @return 删除
     */
    @GetMapping("/delRole")
    @ApiOperation(value = "删除项目", notes = "删除项目")
    public Result delRole(@RequestParam Long projectId) {
        sysProjectService.removeById(projectId);
        return Result.ok();
    }

    /**
     * 上传文件
     */
    @ApiOperation(value = "上传文件（需求文档、项目章程）", notes = "上传文件需求文档、项目章程）")
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
        Result<JSONObject> result = new Result<JSONObject>();
        if (file.isEmpty()) {
            throw new LException("上传文件不能为空");
        }
        MinioUtils minioUtils = new MinioUtils();
        String fileName = UUID.randomUUID().toString().replace("-","")+file.getOriginalFilename(); //文件名
        ObjectWriteResponse sss = MinioUtils.putObject("data",file,fileName,"file");
        String fileurl = MinioConfig.getEndpoint()+"/"+MinioConfig.getBucketname()+"/"+fileName;
        JSONObject res = new JSONObject();
        res.put("url", fileurl);
        return Result.ok(res);
    }


}

