package com.lana.modules.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务—流程绑定(BusBinding)表实体类
 *
 * @author lyl
 * @since 2022-12-07 21:26:47
 */
@SuppressWarnings("serial")
@Data
@TableName("bus_binding")
public class BusBindingEntity  {
    //主键
    private Long id;
    //任务id
    private Long palnItemId;
    //任务名称
    private String palnItemName;
    //计划名称
    private String palnName;
    //流程名称
    private Long stepId;
    //创建者
    private String createUser;
    //创建时间
    private Date createTime;

    }

