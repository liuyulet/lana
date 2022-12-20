package com.lana.modules.system.pojo.vo;

import lombok.Data;

/**
 * 用户展示表单
 *
 * @auther liuyulet
 */
@Data
public class SysUserRoleDepartVO {

  private long userId;
  private String fullname;
  private String email;
  private String username;
  private String mobile;
  private long status;
  private String departIds;
  private String createUser;
  private java.sql.Timestamp createTime;
  private String departName;
  private long departId;
  private long roleId;
  private String roleName;


}
