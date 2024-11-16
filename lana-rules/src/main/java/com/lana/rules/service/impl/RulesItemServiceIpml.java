package com.lana.rules.service.impl;

import com.lana.base.mybatis.service.impl.BaseServiceImpl;
import com.lana.base.operatelog.enums.GeneralPrefixEnum;
import com.lana.rules.dao.RulesItemDao;
import com.lana.rules.entity.RulesItemEntity;
import com.lana.rules.entity.vo.query.RulesItemQuery;
import com.lana.rules.entity.vo.result.RulesItemResult;
import com.lana.rules.entity.vo.update.RulesItemUpdate;
import com.lana.rules.service.RulesItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @auther liuyulet
 * @date 2024/9/13 21:35
 */
@Slf4j
@Service
public class RulesItemServiceIpml extends BaseServiceImpl<RulesItemDao, RulesItemEntity> implements RulesItemService {

    @Value("${lana.aviator-path}")
    private String aviatorPath;



    @Override
    public RulesItemResult getRules(RulesItemQuery queryVO) {

        RulesItemResult rulesItemResult = baseMapper.getRules(queryVO);

        return rulesItemResult;
    }

    @Override
    public void updateAndSaveRulesItem(RulesItemUpdate updateVO) {

        //修改操作
        if(updateVO.getId()!=null && updateVO.getAsPath() != null){
            // 使用 Path API 获取父目录
            Path path = Paths.get(updateVO.getAsPath());
            Path parentPath = path.getParent();
            // 生成新的文件名
            String newFileName = GeneralPrefixEnum.DEVICE_PREFIX.getValue() + updateVO.getDeviceItemId() + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue();
            // 拼接完整的文件路径
            Path newFilePath = parentPath.resolve(newFileName);
            // 将父目录和新文件名转换回字符串
            String newContentPath = newFilePath.toString();
            //操作文件
            boolean isSuccess = deleteAndCreateFile(updateVO.getAsPath(),newContentPath,updateVO.getAsContent());
            if(isSuccess){
                //修改数据库
                RulesItemEntity rulesItemEntity = new RulesItemEntity();
                rulesItemEntity.setAsName(updateVO.getAsName());
                rulesItemEntity.setAsType(updateVO.getAsType());
                rulesItemEntity.setAsContent(updateVO.getAsContent());
                rulesItemEntity.setAsPath(newContentPath);
                rulesItemEntity.setId(updateVO.getId());
                //更新规则脚本信息
                baseMapper.updateById(rulesItemEntity);
            }
        //新增操作
        }else {
            //保存文件
            String filePath = saveRulesFile(updateVO);
            if(filePath!=null){
                //存储规则脚本信息
                RulesItemEntity rulesItemEntity = new RulesItemEntity();
                rulesItemEntity.setAsPath(filePath);
                rulesItemEntity.setAsName(updateVO.getAsName());
                rulesItemEntity.setAsType(updateVO.getAsType());
                rulesItemEntity.setAsContent(updateVO.getAsContent());
                baseMapper.insert(rulesItemEntity);
                //绑定设备关联关系
                baseMapper.saveRulesDeviceItem(rulesItemEntity.getId(),updateVO.getDeviceItemId());
            }
        }
    }


    /**
     * 修改规则脚本文件
     * @param oldFilePath
     * @param newContentPath
     * @param newContent
     * @return
     */

    public boolean deleteAndCreateFile(String oldFilePath, String newContentPath, String newContent) {

        Path oldFile = Paths.get(oldFilePath);
        Path newFile = Paths.get(newContentPath);

        try {
            // 删除旧文件
            if (Files.exists(oldFile)) {
                Files.delete(oldFile);
                System.out.println("旧文件 " + oldFilePath + " 已经被删除");
            }

            // 创建新文件
            Files.createFile(newFile);
            try (BufferedWriter writer = Files.newBufferedWriter(newFile)) {
                writer.write(newContent);
                System.out.println("新文件 " + newContentPath + " 已经创建并写入成功");
            }
            return true;
        } catch (IOException e) {
            System.err.println("文件操作失败: " + e.getMessage());
            return false;
        }
    }


    /**
     * 新增脚本文件
     * @param saveVO
     */
    public String saveRulesFile(RulesItemUpdate saveVO) {
        String targetDirectory = null;
        if(aviatorPath==null || aviatorPath.isEmpty()){
            String userDir = System.getProperty("user.dir");
            String scriptPath = Paths.get(userDir, "lana-rules","src", "main", "resources", "script").toString();
            targetDirectory = scriptPath;
        }else {
            targetDirectory = aviatorPath;
        }
        String fileName =  GeneralPrefixEnum.DEVICE_PREFIX.getValue()+saveVO.getDeviceItemId()+GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue();
        Path filePath = Paths.get(targetDirectory, fileName);
        try {
            // 创建目录（如果不存在）
            Files.createDirectories(filePath.getParent());
            // 创建文件并写入内容
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
                writer.write(saveVO.getAsContent());
                System.out.println("文件创建并写入成功");
                //返回文件路径
                return filePath.toString();
            }
        } catch (IOException e) {
            System.err.println("文件创建或写入失败: " + e.getMessage());
            //返回文件路径
            return null;
        }
    }

}
