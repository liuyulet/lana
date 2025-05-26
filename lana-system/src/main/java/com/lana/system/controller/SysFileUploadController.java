package com.lana.system.controller;

import com.lana.base.file.service.FileService;
import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.enums.OperateTypeEnum;
import com.lana.system.entity.vo.result.SysFileUploadResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import cn.hutool.core.date.DateUtil;
import java.util.Date;
import cn.hutool.core.io.file.FileNameUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther liuyulet
 * @date 2024/3/30 17:28
 */
@RestController
@RequestMapping("/sys/file")
@Tag(name = "文件上传")
public class SysFileUploadController {
    @Resource
    private FileService storageService;


    @PostMapping("/upload")
    @Operation(summary = "上传")
    @OptLog(type = OperateTypeEnum.INSERT)
    public LanaResult<SysFileUploadResult> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return LanaResult.error("请选择需要上传的文件");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(DateUtil.format(new Date(), "yyyyMMdd"));
        stringBuffer.append("/");
        stringBuffer.append(FileNameUtil.getPrefix(file.getOriginalFilename()));
        stringBuffer.append("_");
        stringBuffer.append(DateUtil.timeToSecond(DateUtil.formatTime(new Date())));
        stringBuffer.append(".");
        stringBuffer.append(FileNameUtil.getSuffix(file.getOriginalFilename()));

        // 上传文件
        String url = storageService.upload(file.getBytes(), stringBuffer.toString());
        SysFileUploadResult vo = new SysFileUploadResult();
        vo.setSrc(url);
        vo.setSize(file.getSize());
        vo.setFileName(file.getOriginalFilename());
        vo.setMsg("minio");

        return LanaResult.ok(vo);
    }

}
