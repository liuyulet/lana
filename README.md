# lana 轻量级物联网设备管理平台

## 1、加入我们的开发旅程，请先读我：
[整体设计说明（会不断地修改完善）](doc%2Fmd%2Fproject.md)


### 演示地址：

[演示地址](http://120.48.56.39:8081)

登录信息：lana/lana.password

请不要恶意攻击！

如果觉着不错请给个star鼓励一下吧!

### 使用该项目请查看[使用说明](https://github.com/liuyulet/lana/wiki)

## 2、项目背景
~~~
项目背景
机缘巧合，有很多小物件想自己去diy，但是没有找到合适的平台管理平台，所以自己动手搞了一个。
它是基于java、vue构建的物联网云平台，用于边缘平台设备的接入，设备的展示以及设备的反控等功能。
吃水不忘挖井人，因为该项目引用了一些开源的项目（具体请看下方技术栈），所以也为开源事业做一份贡献。
多有不足，请多指正！

特点：
● 接入协议简单...
● 专注于云平台的设备管理...
● 通俗易懂的代码与使用习惯，方便后期修改（实际上是自己懒，尽量简单实现）...
● 为后期diy设备做个准备...

目标
1. 完成物联网平台的整体开发，简单部署与使用。
2. 人生很短，做一些自己喜欢做的事情吧。
~~~
## 3、项目所使用的技术栈

后端程序学习并参考了： [ruoyi](https://gitee.com/y_project/RuoYi)

前端项目基于scui修改：[scui](https://gitee.com/lolicode/scui)


| 序号 | 项目架构        | 架构描述                                                                                                                                                                                                                                                           | 
|----|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1  | 后端技术栈          | [springboot3.3](https://github.com/spring-projects/spring-boot) + [springSecurity6.2](https://github.com/spring-projects/spring-security) + [Mybatis-Plus3.5.5](https://github.com/baomidou/mybatis-plus) + [jwt0.11](https://github.com/jwtk/jjwt) + [AviatorScript5.4.3](https://github.com/killme2008/aviatorscript) + [mica-mqtt2.4.4](https://gitee.com/dromara/mica-mqtt) + [caffeine3.1.8](https://github.com/ben-manes/caffeine) + [redisson3.21.3](https://github.com/redisson/redisson)  + [hutool5.8.21](https://github.com/chinabugotech/hutool) + [captcha1.6.2](https://github.com/ele-admin/EasyCaptcha) + [knife4j4.3](https://github.com/xiaoymin/knife4j) + [mapstruct1.5.5](https://github.com/mapstruct/mapstruct) + [dynamic4.2](https://github.com/baomidou/dynamic-datasource) ... |
| 2  | 必要的服务          |  mysql8 + TDengine3.2 + redis7.0 + minio8.5.1 + jdk17 + node.js |
| 3  | 前端技术栈          | Vue3 + Element-Plus（由[scui](https://gitee.com/lolicode/scui)项目进行修改开发）                                                                                                                                                                                       |

### 在此感谢各位大佬们、前辈们的开源！为我点燃了前进的航路！


## 4、项目结构（本项目为前后端分离项目）
~~~
|----lana               
|----lana-abutment      #设备接入模块
|----lana-base          #基础功能模块
|----lana-device        #设备管理模块
|----lana-rules         #规则管理模块
|----lana-service       #主服务模块
|----lana-system        #系统管理模块
.....                   #待归化模块
|----lana-web           #前端项目
~~~


# 基础计划大纲1.0版本

## 1、基础框架选型、数据库和缓存整合

| 序号 | 功能模块         | 子功能描述                                                                                                                                                                   | 状态      |
|----|--------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|
| 1  | 基础框架选型       | springboot3.3 + SpringSecurity6.2 + Mybatis-Plus3.5.5 + jwt0.11 + java17  + minio8.5.1 + knife4j4.3 + mapstruct1.5.5 + dynamic4.2 + AviatorScript5.4.3 + mica-mqtt2.4.4 | 已完成     |
| 2  | 常用数据库        | mysql8 + TDengine3.2                                                                                                                                                    | 已完成     |
| 3  | 缓存      | redis7.0 + caffeine3.1.8                                                                                                                                                | 已完成     |
| 4  | 基础 base 封装   | 安全认证封装、缓存实现与封装、文件上传下载封装、数据库mybatis封装、<br/>接口操作日志封装、knife4j接口文档封装、全局异常封装、默认分页与返回结果封装<br/>、默认树形节点处理、xss封装 、通用工具类处理     | 已完成     |
| 5  | 基础 system 功能 | 详见下方表格                                                                                                                                        | 已完成     |


## 2、基础 system 功能

| 序号 | 功能模块   | 子功能描述                              | 状态               |
|----|--------|------------------------------------|------------------|
| 1  | 字典管理   | 字典增删改查，封装方法                        | 已完成（仅是台账，暂时没有使用） |
| 2  | 部门管理   | 部门增删改查，以及关联关系                      | 已完成              |
| 3  | 菜单管理   | 进行增删改查，以及接口权限的设置                   | 已完成              |
| 4  | 角色权限管理 | 进行增删改查，菜单权限、数据权限                   | 已完成              |
| 5  | 用户管理   | 进行增删改查，进行组织与员工的联查，实现角色分配，与密码的重置    | 已完成              |
| 6  | 不同账户管理 | 登录注册、不同角色权限菜单调整                    | 已完成              |
| 7  | 日志管理   | 将所有的操作日志进行记录与查询                    | 已完成              |
| 8  | 代码优化   | 持续修改、优化代码、解决不合理的bug、调整为符合最新项目进度的结构 | 进行中              |


# 功能计划大纲1.0版本（进行中）
## 1、服务端功能进展
| 序号 | 功能模块 | 子功能描述 | 描述                    | 状态  |
|----|------|-------|-----------------------|-----|
| 1  | 设备信息 | 产品管理  | 产品配置，以及设置产品的具体属性和接入信息 | 已完成 |
| 2  | 设备信息 | 设备管理  | 配置具体的设备信息             | 已完成 |
| 3  | 设备信息 | 设备分组  | 将设备进行分组处理             | 已完成 |
| 4  | 接入管理 | 接入协议  | 主要是维护在系统中，设备的接入方式     | 已完成 |
| 5  | 接入管理 | 客户端管理 | 主要是对已经连接的客户端的管理       | 未开始 |
| 6  | 规则编排 | 情景模式  | 通过图形化节点的方式维护情景模式      | 已完成 |
| 7  | 规则事件 | 情景日志  | 展示情景模式中的执行日志          | 已完成 |
| 8  | 预警信息 | 消息记录  | 符合预警消息的数据会被处理成预警消息    | 已完成 |
| 9  | 组态管理 | 组态设置  | 将设备信息在2D大屏上进行设置       | 未开始 |
| 10 | 组态管理 | 组态展示  | 2D大屏展示                | 未开始 |

## 2、边缘端功能进展

| 序号 | 功能模块 | 子功能描述                                                                                              | 状态       |
|----|------|----------------------------------------------------------------------------------------------------|----------|
| 1  | 设备管理 | 支持PLC(西门子、欧姆龙、三菱)、modbus、modbus-tcp、modbus-rtu、mqtt<br/>、http、tcp、udp、websocket、mqtt-ws协议，并接入对应的设备 | 未开始      |
| 2  | 认证同步 | 统一边缘认证，支持设备信息同步、支持设备认证、以及设备数据上报、云边联动                                                               | 未开始      |


## 后续版本设计

[2.0 版本计划](doc%2Fmd%2Ftwo.md)

[3.0 版本计划](doc%2Fmd%2Fthree.md)



# 图片一览
![shouye.PNG](doc%2Fimg%2Fshouye.PNG)
![shouye2.PNG](doc%2Fimg%2Fshouye2.PNG)
![people.PNG](doc%2Fimg%2Fpeople.PNG)
![log.PNG](doc%2Fimg%2Flog.PNG)
![user.PNG](doc%2Fimg%2Fuser.PNG)
![role.PNG](doc%2Fimg%2Frole.PNG)
![post.PNG](doc%2Fimg%2Fpost.PNG)
![dict.PNG](doc%2Fimg%2Fdict.PNG)
![menus.PNG](doc%2Fimg%2Fmenus.PNG)

![product.png](doc%2Fimg%2Fproduct.png)
![productmode.png](doc%2Fimg%2Fproductmode.png)
![device.png](doc%2Fimg%2Fdevice.png)
![group.png](doc%2Fimg%2Fgroup.png)
![groupbind.png](doc%2Fimg%2Fgroupbind.png)
![protocols.jpg](doc%2Fimg%2Fprotocols.jpg)
![protocols_detil.png](doc%2Fimg%2Fprotocols_detil.png)
![rules.PNG](doc%2Fimg%2Frules.PNG)
![starts.png](doc%2Fimg%2Fstarts.png)
![condition.png](doc%2Fimg%2Fcondition.png)
![control.png](doc%2Fimg%2Fcontrol.png)
![circulation.png](doc%2Fimg%2Fcirculation.png)
![scenes.png](doc%2Fimg%2Fscenes.png)
