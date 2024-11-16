/*
 Navicat Premium Data Transfer

 Source Server         : tecent
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : 12am.cn:23306
 Source Schema         : lana-boot

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 11/11/2024 16:57:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device_abutment
-- ----------------------------
DROP TABLE IF EXISTS `device_abutment`;
CREATE TABLE `device_abutment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `agreement_id` bigint(0) NULL DEFAULT NULL COMMENT 'mqtt协议表id',
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '设备id（这里的用的是产品的id）',
  `device_type` int(0) NULL DEFAULT NULL COMMENT '设备接入的方式，1直链设备，2边缘计算设备，3边缘计算子设备',
  `edge_id` bigint(0) NULL DEFAULT NULL COMMENT '如果是边缘子设备，则这里就是关联的边缘计算id；如果是边缘计算，这里就是边缘计算的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '接入管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_abutment
-- ----------------------------
INSERT INTO `device_abutment` VALUES (12, 1, 2, 1, NULL);
INSERT INTO `device_abutment` VALUES (15, 1, 4, 2, NULL);
INSERT INTO `device_abutment` VALUES (21, 1, 1, 2, 4);

-- ----------------------------
-- Table structure for device_edges_item
-- ----------------------------
DROP TABLE IF EXISTS `device_edges_item`;
CREATE TABLE `device_edges_item`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `edge_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '边缘编码（非必填）',
  `edge_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '边缘名称',
  `edge_firmware_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '固件版本',
  `edge_secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '秘钥',
  `edge_secret_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '秘钥id',
  `edge_lon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '经度',
  `edge_lat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '维度',
  `edge_product_type_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属产品类型id',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '边缘计算实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_edges_item
-- ----------------------------
INSERT INTO `device_edges_item` VALUES (4, 'qw', 'qw', NULL, 'qw', 'qw', 'qw', 'qw', '1', 0, 1, '2024-11-01 17:52:55', 1, '2024-11-08 17:44:12', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for device_group
-- ----------------------------
DROP TABLE IF EXISTS `device_group`;
CREATE TABLE `device_group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '1是0否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分组说明',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_group
-- ----------------------------
INSERT INTO `device_group` VALUES (2, '下班回家组', 1, 1, '下班后，打开灯光，拉上窗帘', 0, 1, '2024-08-02 15:29:52', 1, '2024-10-19 21:40:30', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_group` VALUES (3, '上班离家组', 2, 1, '上班出门，关闭灯光，打开窗帘', 0, 1, '2024-08-07 10:03:00', 1, '2024-10-19 21:40:58', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_group` VALUES (4, '上班离家组', 1, 1, '上班出门', 1, 1, '2024-08-07 10:03:01', 1, '2024-08-07 10:03:01', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_group` VALUES (5, '周末休息组', 3, 1, '周末休闲娱乐组', 0, 1, '2024-08-07 10:04:19', 1, '2024-10-19 21:41:36', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for device_item
-- ----------------------------
DROP TABLE IF EXISTS `device_item`;
CREATE TABLE `device_item`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `imgs` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备图片',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备编码',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '设备状态1正常是0离线',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备说明',
  `product_type_id` bigint(0) NULL DEFAULT NULL COMMENT '所属上级产品类型id',
  `product_type_show` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所属产品树结构id，一般用于回显',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_item
-- ----------------------------
INSERT INTO `device_item` VALUES (32, 'http://121.5.79.252:9000/dev/20240822/2d6aa7e497a059df30d635667b1ec998_61681.jpeg', '空气检测设备', 'sd00001', 1, '测试', 2, '1,2', 0, 1, '2024-08-22 17:08:23', 1, '2024-09-30 17:13:40', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (36, 'http://121.5.79.252:9000/dev/20240914/2d6aa7e497a059df30d635667b1ec998_58755.jpeg', '空气站', 'sadas', 1, '撒旦撒', 2, '1,2', 0, 1, '2024-09-14 16:19:36', 1, '2024-09-14 16:19:36', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (37, 'http://121.5.79.252:9000/dev/20241024/1_54155.png', '加湿器', 'jiashiq', 1, '测试测试', 2, '1,2', 0, 1, '2024-10-24 15:02:54', 1, '2024-10-24 21:30:30', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_item` VALUES (38, 'http://121.5.79.252:9000/dev/20241025/2_57394.png', '灯光控制器', 'dg', 1, '边缘计算灯光控制器', 4, '4', 0, 1, '2024-10-25 15:57:15', 1, '2024-10-25 15:57:15', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for device_item_group
-- ----------------------------
DROP TABLE IF EXISTS `device_item_group`;
CREATE TABLE `device_item_group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_group_id` bigint(0) NULL DEFAULT NULL COMMENT '设备分组表id',
  `device_item_id` bigint(0) NULL DEFAULT NULL COMMENT '设备实例表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备分组关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_item_group
-- ----------------------------
INSERT INTO `device_item_group` VALUES (36, 2, 32);
INSERT INTO `device_item_group` VALUES (37, 3, 36);

-- ----------------------------
-- Table structure for device_mode
-- ----------------------------
DROP TABLE IF EXISTS `device_mode`;
CREATE TABLE `device_mode`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mode_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名称',
  `mode_signs` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性标志',
  `mode_data_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据类型',
  `mode_data_source` tinyint(0) NULL DEFAULT NULL COMMENT '数据来源',
  `mode_control_att` tinyint(0) NULL DEFAULT NULL COMMENT '控制属性  0：否   1：是',
  `mode_remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补充说明',
  `device_item_id` bigint(0) NULL DEFAULT NULL COMMENT '设备id',
  `defult_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '默认值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 175 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '设备物模型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_mode
-- ----------------------------
INSERT INTO `device_mode` VALUES (151, '温度', 'wd', 'float', 1, 0, '温度', 32, NULL);
INSERT INTO `device_mode` VALUES (152, '湿度', 'sd', 'float', 1, 0, '湿度', 32, NULL);
INSERT INTO `device_mode` VALUES (153, 'PM2.5', 'pm25', 'float', 1, 0, 'PM2.5', 32, NULL);
INSERT INTO `device_mode` VALUES (154, 'PM100', 'PM100', 'float', 0, 0, 'PM100', 32, NULL);
INSERT INTO `device_mode` VALUES (155, '开', 'open', 'int', 0, 1, '开指令', 32, '1');
INSERT INTO `device_mode` VALUES (156, '关', 'shutdown', 'int', 0, 1, '关指令', 32, '2');
INSERT INTO `device_mode` VALUES (157, '温度', 'wd', 'float', 1, 0, '温度', 36, NULL);
INSERT INTO `device_mode` VALUES (158, '湿度', 'sd', 'float', 1, 0, '湿度', 36, NULL);
INSERT INTO `device_mode` VALUES (159, 'PM25', 'pm25', 'float', 1, 0, '灰尘', 36, NULL);
INSERT INTO `device_mode` VALUES (168, '开', 'on_device', 'int', 0, 1, '开属性', 38, '1');
INSERT INTO `device_mode` VALUES (169, '关', 'off_device', 'int', 0, 1, '关属性', 38, '2');
INSERT INTO `device_mode` VALUES (170, '湿度', 'sd', 'float', 0, 0, '湿度', 38, NULL);
INSERT INTO `device_mode` VALUES (171, '温度', 'wd', 'float', 0, 0, '温度', 38, NULL);
INSERT INTO `device_mode` VALUES (172, '温度', 'wd', 'float', 1, 0, '温度', 37, NULL);
INSERT INTO `device_mode` VALUES (173, '湿度', 'sd', 'float', 1, 0, '湿度', 37, NULL);
INSERT INTO `device_mode` VALUES (174, 'PM2.0', 'pm25', 'float', 1, 0, '灰尘', 37, NULL);

-- ----------------------------
-- Table structure for device_product_mode
-- ----------------------------
DROP TABLE IF EXISTS `device_product_mode`;
CREATE TABLE `device_product_mode`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mode_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性名称',
  `mode_signs` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '属性标志',
  `mode_data_type` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据类型',
  `mode_remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补充说明',
  `product_type_id` bigint(0) NULL DEFAULT NULL COMMENT '产品类型id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品物模型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_product_mode
-- ----------------------------
INSERT INTO `device_product_mode` VALUES (4, '测试属性', 'test', 'string', '测试使用', 1);
INSERT INTO `device_product_mode` VALUES (5, '测试属性2', 'test2', 'double', '测试使用2', 1);
INSERT INTO `device_product_mode` VALUES (6, '温度', 'wd', 'float', '温度', 2);
INSERT INTO `device_product_mode` VALUES (7, '湿度', 'sd', 'float', '湿度', 2);
INSERT INTO `device_product_mode` VALUES (8, 'PM2.0', 'pm2', 'float', '灰尘', 2);

-- ----------------------------
-- Table structure for device_product_type
-- ----------------------------
DROP TABLE IF EXISTS `device_product_type`;
CREATE TABLE `device_product_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级ID',
  `label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '1是0否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_product_type
-- ----------------------------
INSERT INTO `device_product_type` VALUES (1, NULL, '智慧工地', 1, 1, '智慧工地总控制柜', 0, 1, '2024-07-31 11:45:53', 1, '2024-10-25 15:33:19', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (2, 1, '智慧塔吊', 1, 1, '塔吊设备', 0, 1, '2024-07-31 11:46:35', 1, '2024-10-25 15:33:01', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (3, 1, '智能雾炮', 1, 1, '雾炮设备', 0, 1, '2024-07-31 11:47:18', 1, '2024-10-25 15:32:54', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (4, NULL, '灯光控制器', 1, 1, '', 0, 1, '2024-10-25 15:36:49', 1, '2024-10-25 15:37:12', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (5, 4, '塔吊灯', 1, 1, '', 0, 1, '2024-10-25 15:37:05', 1, '2024-10-25 15:37:05', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (6, 4, '路灯', 1, 1, '', 0, 1, '2024-10-25 15:37:23', 1, '2024-10-25 15:37:23', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `device_product_type` VALUES (7, 4, '警示灯', 1, 1, '', 0, 1, '2024-10-25 15:37:36', 1, '2024-10-25 15:37:36', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for protocols_mqtt
-- ----------------------------
DROP TABLE IF EXISTS `protocols_mqtt`;
CREATE TABLE `protocols_mqtt`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt IP地址（所部署的服务器的ip）',
  `mqtt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt名称',
  `mqtt_type` int(0) NULL DEFAULT NULL COMMENT '1:本地mqtt，2：代理mqtt（emqx）',
  `tcp_port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'tcp端口，默认：1883',
  `websocket_port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http、websocket 端口，默认：8083',
  `auth_enable` tinyint(0) NULL DEFAULT NULL COMMENT 'mqtttcp是否开启认证1是0否',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt 认证用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'mqtt 认证密码',
  `http_enable` tinyint(0) NULL DEFAULT NULL COMMENT 'http-api是否开启,1是0否',
  `http_basic_auth` tinyint(0) NULL DEFAULT NULL COMMENT 'http-api如果开启，则设置该选项是否开启 http basic auth，默认： false（0）',
  `http_basic_username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http basic auth 用户名',
  `http_basic_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'http basic auth 密码',
  `ssl_enabled` tinyint(0) NULL DEFAULT NULL COMMENT '是否开启 ssl 认证,1是0否',
  `keystore_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '必须参数：ssl keystore 目录',
  `keystore_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '必选参数：ssl keystore 密码',
  `client_auth` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否需要客户端认证（双向认证），默认：NONE（不需要），REQUIRE（需要）',
  `truststore_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可选参数：ssl 双向认证 truststore 目录',
  `truststore_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可选参数：ssl 双向认证 truststore 密码',
  `mqtt_enabled` tinyint(0) NULL DEFAULT NULL COMMENT 'mqtt是否启用1是0否',
  `link_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '协议-mqtt' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of protocols_mqtt
-- ----------------------------
INSERT INTO `protocols_mqtt` VALUES (1, '0.0.0.0', 'Lana-Mqtt-Server', 1, '1883', '8083', 1, 'lanaMqtt', 'lanaMqtt', 1, 1, 'lanaMqtt', 'lanaMqtt', 0, '', '', 'none', '', '', 1, '0', 0, 1, '2024-11-11 16:30:39', 1, '2024-11-11 16:30:39', '系统初始化', '系统初始化');
INSERT INTO `protocols_mqtt` VALUES (2, '12am.cn', '其他emqx代理', 2, '1884', '8084', 1, 'admin', 'admin1234!@#$', 0, NULL, '', '', 0, '', '', 'NONE', '', '', 0, '0', 1, 1, '2024-09-04 16:51:28', 1, '2024-09-04 18:21:41', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for rules_device_item
-- ----------------------------
DROP TABLE IF EXISTS `rules_device_item`;
CREATE TABLE `rules_device_item`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `device_id` bigint(0) NULL DEFAULT NULL COMMENT '设备实体表id',
  `rules_item_id` bigint(0) NULL DEFAULT NULL COMMENT '规则表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则-设备关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_device_item
-- ----------------------------
INSERT INTO `rules_device_item` VALUES (1, 32, 1);
INSERT INTO `rules_device_item` VALUES (3, 36, 3);

-- ----------------------------
-- Table structure for rules_item
-- ----------------------------
DROP TABLE IF EXISTS `rules_item`;
CREATE TABLE `rules_item`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `as_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '规则脚本名称',
  `as_type` tinyint(0) NULL DEFAULT NULL COMMENT '1:脚本，2:表达式，3：图形化配置',
  `as_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '表达式内容',
  `as_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '脚本路径',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '规则实例' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules_item
-- ----------------------------
INSERT INTO `rules_item` VALUES (1, 'hello', 1, '## script/hello.av\n\nuse java.util.*;\n\nlet results = new ArrayList();\n\nif (wd &gt; 50) {\n    seq.add(results, \"温度大于50: \" + wd);\n} else {\n    seq.add(results, \"温度小于50: \" + wd);\n}\n\nif (sd &gt; 70) {\n    seq.add(results, \"湿度大于70: \" + sd);\n} else {\n    seq.add(results, \"湿度小于70: \" + sd);\n}\n\nif (pm25 &gt; 70) {\n    seq.add(results, \"PM2.5 大于70: \" + pm25);\n} else {\n    seq.add(results, \"PM2.5 小于70: \" + pm25);\n}\n\nreturn results;', '/Users/liuyulong/IdeaProjects/lana/lana-rules/src/main/resources/script/SB32.av', 0, 1, '2024-09-24 14:46:53', 1, '2024-10-26 16:55:26', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `rules_item` VALUES (3, '测试2', 1, '## script/hello.av\n\nuse java.util.*;\n\nlet results = new ArrayList();\n\nif (wd &gt; 80) {\n    seq.add(results, \"温度大于80: \" + wd);\n} else {\n    seq.add(results, \"温度小于80: \" + wd);\n}\n\nif (sd &gt; 60) {\n    seq.add(results, \"湿度大于60: \" + sd);\n} else {\n    seq.add(results, \"湿度小于60: \" + sd);\n}\n\nif (pm25 &gt; 750) {\n    seq.add(results, \"PM2.5 大于75: \" + pm25);\n} else {\n    seq.add(results, \"PM2.5 小于75: \" + pm25);\n}\n\nreturn results;', '/Users/liuyulong/IdeaProjects/lana/lana-rules/src/main/resources/script/SB36.av', 0, 1, '2024-09-25 14:46:30', 1, '2024-10-26 16:54:42', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dic` bigint(0) NOT NULL COMMENT '字典类型ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名字',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典值',
  `label_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签样式',
  `yx` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有效',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典子项数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '停用', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-07-05 21:00:39', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (2, 1, '正常', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (3, 2, '男', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (4, 2, '女', '1', 'success', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (5, 2, '未知', '2', 'warning', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (6, 3, '正常', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (7, 3, '停用', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (8, 4, '全部数据', '0', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (9, 4, '本机构及子机构数据', '1', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (10, 4, '本机构数据', '2', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (11, 4, '本人数据', '3', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (12, 4, '自定义数据', '4', '', '1', 0, 1, 1, '2024-01-10 06:13:37', 1, '2024-07-07 22:18:02', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (13, 5, '禁用', '0', 'danger', '1', 1, 1, 1, '2024-01-10 06:13:37', 1, '2024-04-26 13:58:57', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (14, 5, '启用', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (15, 6, '失败', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (16, 6, '成功', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (17, 7, '登录成功', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (18, 7, '退出成功', '1', 'warning', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (19, 7, '验证码错误', '2', 'danger', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (20, 7, '账号密码错误', '3', 'danger', '1', 3, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (21, 8, '否', '0', 'primary', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (22, 8, '是', '1', 'danger', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (23, 9, '是', '1', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (24, 9, '否', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (25, 10, '其它', '0', 'info', '1', 10, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (26, 10, '查询', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (27, 10, '新增', '2', 'success', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (28, 10, '修改', '3', 'warning', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (29, 10, '删除', '4', 'danger', '1', 3, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (30, 10, '导出', '5', 'info', '1', 4, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (31, 10, '导入', '6', 'info', '1', 5, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (36, 12, '默认', 'default', '', '1', 0, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (37, 12, '系统', 'system', '', '1', 1, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (38, 13, '暂停', '0', 'danger', '1', 0, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (39, 13, '正常', '1', 'primary', '1', 1, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (45, 13, '其他2', '3', NULL, '0', NULL, 1, 1, '2024-04-26 17:38:20', 1, '2024-04-26 17:40:18', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` VALUES (46, 13, '1', '1', NULL, '1', 4, 1, 1, '2024-04-26 17:47:55', 1, '2024-04-28 10:38:24', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1714118012721 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '字典类型数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, 'post_status', '请求状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-04-21 16:08:57', NULL, '[超级管理员:admin]');
INSERT INTO `sys_dict_type` VALUES (2, 'user_gender', '性别', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (3, 'user_status', '用户状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (4, 'role_data_scope', '数据范围', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (5, 'enable_disable', '启用状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (6, 'success_fail', '是否成功', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (7, 'login_operation', '操作信息', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (8, 'params_type', '系统参数', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (9, 'user_super_admin', '用户是否是超管', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (10, 'log_operate_type', '操作类型', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (11, 'sms_platform', '平台类型', 1, 1, '2024-01-10 06:14:40', 1, '2024-01-10 06:14:40', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (12, 'schedule_group', '任务组名', 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (13, 'schedule_status', '状态', 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', NULL, NULL);

-- ----------------------------
-- Table structure for sys_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate`;
CREATE TABLE `sys_log_operate`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '模块名',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作名',
  `req_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方法',
  `req_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '请求参数',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作IP',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'User Agent',
  `operate_type` tinyint(0) NULL DEFAULT NULL COMMENT '操作类型',
  `duration` int(0) NOT NULL COMMENT '执行时长',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '操作状态  0：失败   1：成功',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2864 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log_operate
-- ----------------------------
INSERT INTO `sys_log_operate` VALUES (2822, '设备分组', '所属分组分页查询', '/lana/devices/deviceGroup/groupDeviceItemPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"groupId\":5}]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 08:49:16');
INSERT INTO `sys_log_operate` VALUES (2823, '设备分组', '所属分组分页查询', '/lana/devices/deviceGroup/groupDeviceItemList', 'GET', '[2]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 08:49:26');
INSERT INTO `sys_log_operate` VALUES (2824, '产品分类', '列表', '/lana/devices/deviceProductType/list', 'GET', '[{\"label\":null}]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 09:08:58');
INSERT INTO `sys_log_operate` VALUES (2825, '菜单管理', '菜单导航', '/lana/sys/menus/nav', 'GET', NULL, '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 10:39:05');
INSERT INTO `sys_log_operate` VALUES (2826, '产品分类', '列表', '/lana/devices/deviceProductType/list', 'GET', '[{\"label\":null}]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 10:39:05');
INSERT INTO `sys_log_operate` VALUES (2859, '设备产品物模型', '列表查询', '/lana/devices/deviceProductMode/list', 'GET', '[1]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 1, 1, 1, '超级管理员', '2024-08-20 18:03:26');
INSERT INTO `sys_log_operate` VALUES (2860, '设备产品物模型', '保存', '/lana/devices/deviceProductMode/save', 'POST', '[{\"deviceProductModeListSave\":[{\"id\":null,\"modeName\":\"测试\",\"modeSigns\":\"test\",\"modeDataType\":\"string\",\"modeDataSource\":\"0\",\"modeControlAtt\":1,\"modeRemark\":\"这个是测试\"},{\"id\":null,\"modeName\":\"测试2\",\"modeSigns\":\"test2\",\"modeDataType\":\"string\",\"modeDataSource\":\"0\",\"modeControlAtt\":1,\"modeRemark\":\"这个是测试2\"}],\"productTypeId\":1}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 2, 0, 1, 1, '超级管理员', '2024-08-20 18:04:06');
INSERT INTO `sys_log_operate` VALUES (2861, '设备产品物模型', '列表查询', '/lana/devices/deviceProductMode/list', 'GET', '[1]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 1, 1, 1, '超级管理员', '2024-08-20 18:04:16');
INSERT INTO `sys_log_operate` VALUES (2862, '设备产品物模型', '列表查询', '/lana/devices/deviceProductMode/list', 'GET', '[1]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-20 18:05:17');
INSERT INTO `sys_log_operate` VALUES (2863, '菜单管理', '菜单导航', '/lana/sys/menus/nav', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 1, 1, 1, '超级管理员', '2024-10-09 21:52:45');

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单英文名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单页面地址',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '内容',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单请求地址,可不填',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示名称。展示在菜单，标签和面包屑等中',
  `hidden` tinyint(0) NULL DEFAULT NULL COMMENT '是否隐藏菜单，大部分用在无需显示在左侧菜单中的页面，比如详情页',
  `affix` tinyint(0) NULL DEFAULT NULL COMMENT '是否固定，类似首页控制台在标签中是没有关闭按钮的',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '显示图标，建立2级菜单都设置图标，否则菜单折叠都将显示空白',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型：菜单，Iframe，外链，按钮',
  `hiddenBreadcrumb` tinyint(0) NULL DEFAULT NULL COMMENT '是否隐藏面包屑',
  `active` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '左侧菜单的路由地址活动状态，比如打开详情页需要列表页的菜单活动状态',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '颜色值',
  `fullpage` tinyint(0) NULL DEFAULT NULL COMMENT '是否整页打开路由（脱离框架系），例如：fullpage: true',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '静态路由时，所能访问路由的角色，例如：role: [\"SA\"]',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 242 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES (75, NULL, 'home', '/home', 'home', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '首页', NULL, NULL, 'el-icon-home-filled', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:17', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (76, 75, 'dashboard', '/dashboard', 'home', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '控制台', NULL, NULL, 'el-icon-menu', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-10 23:26:08', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (77, 75, 'userCenter', '/usercenter', 'userCenter', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '帐号信息', NULL, NULL, 'el-icon-avatar', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:09:43', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (78, NULL, 'setting', '/setting', 'setting', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '系统设置', NULL, NULL, 'el-icon-setting', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:08:42', 1, '2024-05-09 10:03:51', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (80, 78, 'user', '/setting/user', 'setting/user', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '用户管理', NULL, NULL, 'el-icon-user', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (81, 78, 'role', '/setting/role', 'setting/role', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '角色管理', NULL, NULL, 'el-icon-operation', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:06:57', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (82, 78, 'dept', '/setting/dept', 'setting/dept', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '部门管理', NULL, NULL, 'sc-icon-organization', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (83, 78, 'dic', '/setting/dic', 'setting/dic', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '字典管理', NULL, NULL, 'el-icon-cellphone', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:28', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (85, 78, 'settingMenu', '/setting/menu', 'setting/menu', NULL, NULL, NULL, 5, 1, '[超级管理员:admin]', 0, '菜单管理', NULL, NULL, 'el-icon-menu', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (86, NULL, 'devices', '/devices', 'devices', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '设备信息', NULL, NULL, 'el-icon-message-box', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-26 14:34:18', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (87, 86, 'devices:product', '/devices/product', 'devices/product', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 1, '测试分组', NULL, NULL, 'el-icon-cpu', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-29 17:55:31', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (88, 86, 'devices:deviceItem', '/devices/deviceItem', 'devices/deviceItem', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '设备管理', NULL, NULL, 'el-icon-monitor', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 15:51:04', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (89, 86, 'devices:deviceGroup', '/devices/deviceGroup', 'devices/deviceGroup', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '设备分组', NULL, NULL, 'el-icon-folder-add', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:34:10', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (90, NULL, 'abutment', '/abutment', 'abutment', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '接入管理', NULL, NULL, 'el-icon-expand', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-26 17:35:50', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (91, 90, 'abutment:deviceComm', '/abutment/communica', 'abutment/communica', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 1, '通讯组件', NULL, NULL, 'el-icon-guide', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (92, 90, 'abutment:protocols', '/abutment/protocols', 'abutment/protocols', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '接入协议', NULL, NULL, 'el-icon-link', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:31', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (93, 90, 'abutment:edges', '/abutment/edges', 'abutment/edges', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 1, '边缘计算', NULL, NULL, 'el-icon-connection', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (94, 86, 'devices:productType', '/devices/productType', 'devices/productType', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '产品管理', NULL, NULL, 'el-icon-copy-document', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 15:51:35', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (95, 90, 'abutment:pushData', '/abutment/pushData', 'abutment/pushData', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '数据推送', NULL, NULL, 'el-icon-connection', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-26 17:24:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (96, NULL, 'warnings', '/warnings', 'warnings', NULL, NULL, NULL, 6, 1, '[超级管理员:admin]', 0, '预警信息', NULL, NULL, 'el-icon-warning', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-30 11:49:45', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (97, 96, 'warnings:settings', '/warnings/settings', 'warnings/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '预警设置', NULL, NULL, 'el-icon-warning', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 11:49:56', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (98, 96, 'warnings:messages', '/warnings/messages', 'warnings/messages', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '消息记录', NULL, NULL, 'el-icon-message', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 11:46:29', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (99, NULL, 'rules', '/rules', 'rules', NULL, NULL, NULL, 5, 1, '[超级管理员:admin]', 0, '规则事件', NULL, NULL, 'el-icon-scale-to-original', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-30 11:49:12', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (100, 99, 'rules:settings', '/rules/settings', 'rules/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '规则设置', NULL, NULL, 'el-icon-setting', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 11:45:59', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (101, 99, 'rules:linkage', '/rules/linkage', 'rules/linkage', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '场景联动', NULL, NULL, 'el-icon-switch', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 11:46:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (102, NULL, 'combination', '/combination', 'combination', NULL, NULL, NULL, 7, 1, '[超级管理员:admin]', 0, '组态管理', NULL, NULL, 'el-icon-files', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 14:32:31', 1, '2024-07-30 16:37:01', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (103, 102, 'combination:settings', '/combination/settings', 'combination/settings', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '组态设置', NULL, NULL, 'el-icon-crop', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 16:37:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (104, 102, 'combination:showing', '/combination/showing', 'combination/showing', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '组态展示', NULL, NULL, 'el-icon-full-screen', 'menu', NULL, NULL, NULL, NULL, NULL, '2024-07-26 15:34:22', 1, '2024-07-30 16:37:33', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (105, 78, 'system', '/setting/system', 'setting/system', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 1, '系统设置', NULL, NULL, 'el-icon-user', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (200, 85, 'menus.save', '', '', NULL, 'sys:menus:save', 'api/sys/menus/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:26:27', 1, '2024-05-07 17:26:58', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (201, 85, 'menus.update', '', '', NULL, 'sys:menus:update', 'api/sys/menus/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:27:35', 1, '2024-05-07 17:33:13', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (202, 85, 'menus.delete', '', '', NULL, 'sys:menus:delete', 'api/sys/menus/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:33:36', 1, '2024-05-07 17:35:35', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (203, 85, 'menus.list', '', '', NULL, 'sys:menus:list', 'api/sys/menus/list', NULL, 1, '[超级管理员:admin]', 0, '查看', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:36:00', 1, '2024-05-07 17:42:25', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (205, 83, 'dict.save', '', '', NULL, 'sys:dict:save', 'api/sys/dict/save', NULL, 1, '[超级管理员:admin]', 0, '字典类型新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (206, 83, 'dict.update', '', '', NULL, 'sys:dict:update', 'api/sys/dict/update', NULL, 1, '[超级管理员:admin]', 0, '字典类型修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:56:12', 1, '2024-05-08 17:59:49', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (207, 83, 'dict.delete', '', '', NULL, 'sys:dict:delete', 'api/sys/dict/delete', NULL, 1, '[超级管理员:admin]', 0, '字典类型删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:57:46', 1, '2024-05-08 17:59:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (208, 83, 'dict.list', '', '', NULL, 'sys:dict:list', 'api/sys/dict/list', NULL, 1, '[超级管理员:admin]', 0, '字典类型列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (209, 82, 'org.save', '', '', NULL, 'sys:org:save', 'api/sys/org/save', NULL, 1, '[超级管理员:admin]', 0, '保存', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (210, 82, 'org.update', '', '', NULL, 'sys:org:update', 'api/sys/org/update', NULL, 1, '[超级管理员:admin]', 0, '更新', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (211, 82, 'org.delete', '', '', NULL, 'sys:org:delete', 'api/sys/org/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (212, 82, 'org.list', '', '', NULL, 'sys:org:list', 'api/sys/org/list', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (213, 81, 'role.save', '', '', NULL, 'sys:role:save', 'api/sys/role/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (214, 81, 'role.update', '', '', NULL, 'sys:role:update', 'api/sys/role/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (215, 81, 'role.delete', '', '', NULL, 'sys:role:delete', 'api/sys/role/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (216, 81, 'role.page', '', '', NULL, 'sys:role:page', 'api/sys/role/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (217, 80, 'user.page', '', '', NULL, 'sys:user:page', 'api/sys/user/page', NULL, 1, '[超级管理员:admin]', 0, '保存', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (218, 81, 'role.roleLinkMenus', '', '', NULL, 'sys:role:roleLinkMenus', 'api/sys/role/roleLinkMenus', NULL, 1, '[超级管理员:admin]', 0, '权限设置', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (219, 80, 'user.save', '', '', NULL, 'sys:user:save', 'api/sys/user/save', NULL, 1, '[超级管理员:admin]', 0, '新增用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (220, 80, 'user.update', '', '', NULL, 'sys:user:update', 'api/sys/user/update', NULL, 1, '[超级管理员:admin]', 0, '修改用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (221, 80, 'user.delete', '', '', NULL, 'sys:user:delete', 'api/sys/user/delete', NULL, 1, '[超级管理员:admin]', 0, '删除用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (222, 80, 'user.resetPassword', '', '', NULL, 'sys:user:password', 'api/sys/user/resetPassword', NULL, 1, '[超级管理员:admin]', 0, '重置默认密码', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (223, 80, 'user.page', '', '', NULL, 'sys:user:password', 'api/sys/user/page', NULL, 1, '[超级管理员:admin]', 0, '用户列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (224, 83, 'dict.page', '', '', NULL, 'sys:dic:page', 'api/sys/dic/page', NULL, 1, '[超级管理员:admin]', 0, '字典子项列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (225, 83, 'dict.save', '', '', NULL, 'sys:dic:save', 'api/sys/dic/save', NULL, 1, '[超级管理员:admin]', 0, '字典子项新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (226, 83, 'dict.update', '', '', NULL, 'sys:dic:update', 'api/sys/dic/update', NULL, 1, '[超级管理员:admin]', 0, '字典子项修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (227, 83, 'dict.delete', '', '', NULL, 'sys:dic:delete', 'api/sys/dic/delete', NULL, 1, '[超级管理员:admin]', 0, '字典子项删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (228, 94, 'deviceProductType.save', '', '', NULL, 'devices:deviceProductType:save', 'api/devices/deviceProductType/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (229, 94, 'deviceProductType.update', '', '', NULL, 'devices:deviceProductType:update', 'api/devices/deviceProductType/update', NULL, 1, '[超级管理员:admin]', 0, '更新', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (230, 94, 'deviceProductType.delete', '', '', NULL, 'devices:deviceProductType:delete', 'api/devices/deviceProductType/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (231, 94, 'deviceProductType.list', '', '', NULL, 'devices:deviceProductType:list', 'api/devices/deviceProductType/list', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (232, 88, 'deviceItem.page', '', '', NULL, 'devices:deviceItem:page', 'api/devices/deviceItem/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (233, 88, 'deviceItem.save', '', '', NULL, 'devices:deviceItem:save', 'api/devices/deviceItem/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (234, 88, 'deviceItem.update', '', '', NULL, 'devices:deviceItem:update', 'api/devices/deviceItem/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (235, 88, 'deviceItem.delete', '', '', NULL, 'devices:deviceItem:delete', 'api/devices/deviceItem/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (236, 89, 'deviceGroup.page', '', '', NULL, 'devices:deviceGroup:page', 'api/devices/deviceGroup/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (237, 89, 'deviceGroup.save', '', '', NULL, 'devices:deviceGroup:save', 'api/devices/deviceGroup/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (238, 89, 'deviceGroup.update', '', '', NULL, 'devices:deviceGroup:update', 'api/devices/deviceGroup/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` VALUES (239, 89, 'deviceGroup.delete', '', '', NULL, 'devices:deviceGroup:delete', 'api/devices/deviceGroup/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint(0) NULL DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '1是0否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '机构管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, NULL, '鲶鱼科技技术公司', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` VALUES (2, 1, '研发子公司', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` VALUES (3, 1, '风投子公司', 2, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-09-30 17:03:33', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` VALUES (4, 1, '财务子公司', 3, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` VALUES (12, 2, '研发部', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint(0) NULL DEFAULT NULL COMMENT '数据范围  0：全部数据  1：所在部门及子级可见  2：所在部门可见 3：本人数据 ',
  `org_id` bigint(0) NULL DEFAULT NULL COMMENT '机构ID',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `status` bigint(0) NULL DEFAULT 1 COMMENT '是否启用',
  `deleted` tinyint(0) NULL DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_org_id`(`org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '小研发', 'RD', '研发人员', 3, NULL, 7, 1, 1, 1, '2024-01-10 15:30:16', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (2, '技术主管', 'TS', '测试', 1, NULL, 2, 1, 1, 1, '2024-01-10 15:30:31', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (3, '研发组长', 'TL', '开发leader啊', 3, NULL, 3, 1, 1, 1, '2024-01-10 15:31:36', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (4, '小运维', 'OM', '运维人员', 3, NULL, 4, 1, 1, 1, '2024-01-10 15:32:03', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (5, '小产品', 'PM', '是个小产品啊', 3, NULL, 5, 1, 1, 1, '2024-01-10 15:32:51', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (7, '管理员', 'Administrators', '1', 0, NULL, 1, 1, 0, 1, '2024-05-11 13:29:30', 1, '2024-07-16 15:59:13', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_role` VALUES (10, '测试', 'cs', '', 2, NULL, 8, 1, 0, 1, '2024-07-16 17:34:26', 1, '2024-07-16 17:34:26', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 663 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (607, 10, 75);
INSERT INTO `sys_role_menu` VALUES (608, 10, 76);
INSERT INTO `sys_role_menu` VALUES (609, 10, 77);
INSERT INTO `sys_role_menu` VALUES (610, 10, 78);
INSERT INTO `sys_role_menu` VALUES (611, 10, 80);
INSERT INTO `sys_role_menu` VALUES (612, 10, 81);
INSERT INTO `sys_role_menu` VALUES (613, 10, 82);
INSERT INTO `sys_role_menu` VALUES (614, 10, 83);
INSERT INTO `sys_role_menu` VALUES (615, 10, 85);
INSERT INTO `sys_role_menu` VALUES (616, 10, 86);
INSERT INTO `sys_role_menu` VALUES (617, 10, 88);
INSERT INTO `sys_role_menu` VALUES (618, 10, 89);
INSERT INTO `sys_role_menu` VALUES (619, 10, 90);
INSERT INTO `sys_role_menu` VALUES (620, 10, 92);
INSERT INTO `sys_role_menu` VALUES (621, 10, 93);
INSERT INTO `sys_role_menu` VALUES (622, 10, 94);
INSERT INTO `sys_role_menu` VALUES (623, 10, 95);
INSERT INTO `sys_role_menu` VALUES (624, 10, 200);
INSERT INTO `sys_role_menu` VALUES (625, 10, 201);
INSERT INTO `sys_role_menu` VALUES (626, 10, 202);
INSERT INTO `sys_role_menu` VALUES (627, 10, 203);
INSERT INTO `sys_role_menu` VALUES (628, 10, 205);
INSERT INTO `sys_role_menu` VALUES (629, 10, 206);
INSERT INTO `sys_role_menu` VALUES (630, 10, 207);
INSERT INTO `sys_role_menu` VALUES (631, 10, 208);
INSERT INTO `sys_role_menu` VALUES (632, 10, 209);
INSERT INTO `sys_role_menu` VALUES (633, 10, 210);
INSERT INTO `sys_role_menu` VALUES (634, 10, 211);
INSERT INTO `sys_role_menu` VALUES (635, 10, 212);
INSERT INTO `sys_role_menu` VALUES (636, 10, 213);
INSERT INTO `sys_role_menu` VALUES (637, 10, 214);
INSERT INTO `sys_role_menu` VALUES (638, 10, 215);
INSERT INTO `sys_role_menu` VALUES (639, 10, 216);
INSERT INTO `sys_role_menu` VALUES (640, 10, 217);
INSERT INTO `sys_role_menu` VALUES (641, 10, 218);
INSERT INTO `sys_role_menu` VALUES (642, 10, 219);
INSERT INTO `sys_role_menu` VALUES (643, 10, 220);
INSERT INTO `sys_role_menu` VALUES (644, 10, 221);
INSERT INTO `sys_role_menu` VALUES (645, 10, 222);
INSERT INTO `sys_role_menu` VALUES (646, 10, 223);
INSERT INTO `sys_role_menu` VALUES (647, 10, 224);
INSERT INTO `sys_role_menu` VALUES (648, 10, 225);
INSERT INTO `sys_role_menu` VALUES (649, 10, 226);
INSERT INTO `sys_role_menu` VALUES (650, 10, 227);
INSERT INTO `sys_role_menu` VALUES (651, 10, 228);
INSERT INTO `sys_role_menu` VALUES (652, 10, 229);
INSERT INTO `sys_role_menu` VALUES (653, 10, 230);
INSERT INTO `sys_role_menu` VALUES (654, 10, 231);
INSERT INTO `sys_role_menu` VALUES (655, 10, 232);
INSERT INTO `sys_role_menu` VALUES (656, 10, 233);
INSERT INTO `sys_role_menu` VALUES (657, 10, 234);
INSERT INTO `sys_role_menu` VALUES (658, 10, 235);
INSERT INTO `sys_role_menu` VALUES (659, 10, 236);
INSERT INTO `sys_role_menu` VALUES (660, 10, 237);
INSERT INTO `sys_role_menu` VALUES (661, 10, 238);
INSERT INTO `sys_role_menu` VALUES (662, 10, 239);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '填写真实姓名，填写后不允许修改',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint(0) NULL DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人签名',
  `super_admin` tinyint(0) NULL DEFAULT 0 COMMENT '超级管理员   0：否   1：是',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态  0：停用   1：正常',
  `deleted` tinyint(0) NULL DEFAULT 0 COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint(0) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(0) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '{bcrypt}$2a$10$ffEnxNySm7RTtr7PwQm3EeojCZllPUh0n..kYv.sKGkMwbGgqMHE6', '超级管理员', 'http://121.5.79.252:9000/dev/20240618/08089446ef247a095eb42885453c7adc_65902.jpeg', 1, 'lyl@163.com', '13245677645', '三月来百草开 盈香满袖万物苏\n虫鸣和着欢笑 心事舒', 1, 1, 0, 10000, '2024-01-10 06:13:37', 1, '2024-09-07 21:36:13', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_user` VALUES (2, 'lana', '{bcrypt}$2a$10$WjrhS5/p8Dw14trguY/veugQ3mn8GKhiASUOqy8jgfPS7up3fzkjm', 'lana0101', 'http://121.5.79.252:9000/dev/20240903/开发迭代流程_79618.png', 2, 'lyl@163.com', '13245677646', '牛马干活\\韭菜施肥\\蘸料上色', 0, 1, 0, 10000, '2024-07-22 09:49:43', 1, '2024-10-18 12:57:40', '[超级管理员:admin]', '[超级管理员:admin]');

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `org_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_post_id`(`org_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户组织关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
INSERT INTO `sys_user_org` VALUES (42, 1, 1);
INSERT INTO `sys_user_org` VALUES (50, 2, 1);
INSERT INTO `sys_user_org` VALUES (51, 2, 3);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (34, 7, 1);
INSERT INTO `sys_user_role` VALUES (39, 10, 2);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'accessToken',
  `access_token_expire` datetime(0) NULL DEFAULT NULL COMMENT 'accessToken 过期时间',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'refreshToken',
  `refresh_token_expire` datetime(0) NULL DEFAULT NULL COMMENT 'refreshToken 过期时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, 1, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTA1Nzg4NSwiZXhwIjoxNzMxMDU3OTI5fQ.ZMBoNeYJpdtKnvnmiIE96T5ut3lDhnjP0tMqfleh0s8', '2024-11-09 05:24:46', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTA1Nzg4NSwiZXhwIjoxNzMxMDU5MDk1fQ.5aLtEBG2OuePUJq_SzmetskNpL9kmv1Aw5o_b1CEBBs', '2024-11-22 17:24:46', '2024-01-10 14:40:57');
INSERT INTO `sys_user_token` VALUES (2, 2, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsYW5hIiwiaWF0IjoxNzI5MjI4MDI0LCJleHAiOjE3MjkyMjgwNjd9.G32TMkeqyzqSMLLWVBFjcDKZ_tHbtJzM1KCj7l3aGoE', '2024-10-19 01:07:05', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsYW5hIiwiaWF0IjoxNzI5MjI4MDI0LCJleHAiOjE3MjkyMjkyMzR9.6Hv5MRnUyHweuReQ7FjlNpTMavyRBIDl3gwUesLXC3g', '2024-11-01 13:07:05', '2024-01-10 15:38:18');

SET FOREIGN_KEY_CHECKS = 1;
