/*
 Navicat Premium Data Transfer

 Source Server         : tecent
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : 121.5.79.252:23306
 Source Schema         : lana-thin

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 09/10/2024 21:56:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dic` bigint NOT NULL COMMENT '字典类型ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名字',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '字典值',
  `label_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签样式',
  `yx` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '是否有效',
  `sort` int DEFAULT NULL COMMENT '排序',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典子项数据';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (1, 1, '停用', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-07-05 21:00:39', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (2, 1, '正常', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (3, 2, '男', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (4, 2, '女', '1', 'success', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (5, 2, '未知', '2', 'warning', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (6, 3, '正常', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (7, 3, '停用', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (8, 4, '全部数据', '0', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (9, 4, '本机构及子机构数据', '1', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (10, 4, '本机构数据', '2', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (11, 4, '本人数据', '3', '', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (12, 4, '自定义数据', '4', '', '1', 0, 1, 1, '2024-01-10 06:13:37', 1, '2024-07-07 22:18:02', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (13, 5, '禁用', '0', 'danger', '1', 1, 1, 1, '2024-01-10 06:13:37', 1, '2024-04-26 13:58:57', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (14, 5, '启用', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (15, 6, '失败', '0', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (16, 6, '成功', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (17, 7, '登录成功', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (18, 7, '退出成功', '1', 'warning', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (19, 7, '验证码错误', '2', 'danger', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (20, 7, '账号密码错误', '3', 'danger', '1', 3, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (21, 8, '否', '0', 'primary', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (22, 8, '是', '1', 'danger', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (23, 9, '是', '1', 'danger', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (24, 9, '否', '0', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (25, 10, '其它', '0', 'info', '1', 10, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (26, 10, '查询', '1', 'primary', '1', 0, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (27, 10, '新增', '2', 'success', '1', 1, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (28, 10, '修改', '3', 'warning', '1', 2, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (29, 10, '删除', '4', 'danger', '1', 3, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (30, 10, '导出', '5', 'info', '1', 4, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (31, 10, '导入', '6', 'info', '1', 5, 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (36, 12, '默认', 'default', '', '1', 0, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (37, 12, '系统', 'system', '', '1', 1, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (38, 13, '暂停', '0', 'danger', '1', 0, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (39, 13, '正常', '1', 'primary', '1', 1, 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (45, 13, '其他2', '3', NULL, '0', NULL, 1, 1, '2024-04-26 17:38:20', 1, '2024-04-26 17:40:18', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_dict_data` (`id`, `dic`, `name`, `key`, `label_class`, `yx`, `sort`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (46, 13, '1', '1', NULL, '1', 4, 1, 1, '2024-04-26 17:47:55', 1, '2024-04-28 10:38:24', '[超级管理员:admin]', '[超级管理员:admin]');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典名称',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1714118012721 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典类型数据';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (1, 'post_status', '请求状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-04-21 16:08:57', NULL, '[超级管理员:admin]');
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (2, 'user_gender', '性别', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (3, 'user_status', '用户状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (4, 'role_data_scope', '数据范围', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (5, 'enable_disable', '启用状态', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (6, 'success_fail', '是否成功', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (7, 'login_operation', '操作信息', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (8, 'params_type', '系统参数', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (9, 'user_super_admin', '用户是否是超管', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (10, 'log_operate_type', '操作类型', 0, 1, '2024-01-10 06:13:37', 1, '2024-01-10 06:13:37', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (11, 'sms_platform', '平台类型', 1, 1, '2024-01-10 06:14:40', 1, '2024-01-10 06:14:40', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (12, 'schedule_group', '任务组名', 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', NULL, NULL);
INSERT INTO `sys_dict_type` (`id`, `code`, `name`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (13, 'schedule_status', '状态', 0, 1, '2024-01-10 06:15:04', 1, '2024-01-10 06:15:04', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate`;
CREATE TABLE `sys_log_operate` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `module` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模块名',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作名',
  `req_uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `req_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求参数',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作IP',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'User Agent',
  `operate_type` tinyint DEFAULT NULL COMMENT '操作类型',
  `duration` int NOT NULL COMMENT '执行时长',
  `status` tinyint DEFAULT '1' COMMENT '操作状态  0：失败   1：成功',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2873 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='操作日志';

-- ----------------------------
-- Records of sys_log_operate
-- ----------------------------
BEGIN;
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2822, '设备分组', '所属分组分页查询', '/lana/devices/deviceGroup/groupDeviceItemPage', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"groupId\":5}]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 08:49:16');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2823, '设备分组', '所属分组分页查询', '/lana/devices/deviceGroup/groupDeviceItemList', 'GET', '[2]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 08:49:26');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2824, '产品分类', '列表', '/lana/devices/deviceProductType/list', 'GET', '[{\"label\":null}]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 09:08:58');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2825, '菜单管理', '菜单导航', '/lana/sys/menus/nav', 'GET', NULL, '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 10:39:05');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2826, '产品分类', '列表', '/lana/devices/deviceProductType/list', 'GET', '[{\"label\":null}]', '39.71.148.170', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-15 10:39:05');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2859, '设备产品物模型', '列表查询', '/lana/devices/deviceProductMode/list', 'GET', '[1]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 1, 1, 1, '超级管理员', '2024-08-20 18:03:26');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2860, '设备产品物模型', '保存', '/lana/devices/deviceProductMode/save', 'POST', '[{\"deviceProductModeListSave\":[{\"id\":null,\"modeName\":\"测试\",\"modeSigns\":\"test\",\"modeDataType\":\"string\",\"modeDataSource\":\"0\",\"modeControlAtt\":1,\"modeRemark\":\"这个是测试\"},{\"id\":null,\"modeName\":\"测试2\",\"modeSigns\":\"test2\",\"modeDataType\":\"string\",\"modeDataSource\":\"0\",\"modeControlAtt\":1,\"modeRemark\":\"这个是测试2\"}],\"productTypeId\":1}]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 2, 0, 1, 1, '超级管理员', '2024-08-20 18:04:06');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2861, '设备产品物模型', '列表查询', '/lana/devices/deviceProductMode/list', 'GET', '[1]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 1, 1, 1, '超级管理员', '2024-08-20 18:04:16');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2862, '设备产品物模型', '列表查询', '/lana/devices/deviceProductMode/list', 'GET', '[1]', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-08-20 18:05:17');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2863, '菜单管理', '菜单导航', '/lana/sys/menus/nav', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:42');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2864, '角色管理', '分页', '/lana/sys/role/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:42');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2865, '机构管理', '列表', '/lana/sys/org/list', 'GET', '[{\"name\":null}]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:42');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2866, '字典类型', '字典列表', '/lana/sys/dict/list', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:52');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2867, '字典数据', '分页', '/lana/sys/dic/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"code\":\"post_status\"}]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:52');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2868, '菜单管理', '菜单列表', '/lana/sys/menus/list', 'GET', '[null]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:52');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2869, '操作日志', '分页', '/lana/sys/log/operate/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"userId\":1}]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:53:53');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2870, '操作日志', '分页', '/lana/sys/log/operate/page', 'GET', '[{\"page\":1,\"pageSize\":20,\"order\":null,\"asc\":false,\"userId\":1}]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:54:13');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2871, '用户管理', '修改个人密码', '/lana/sys/user/updateMySelfPassword', 'POST', '[{\"username\":\"admin\",\"password\":\"admin123456\",\"newPassword\":\"123456\"}]', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 3, 0, 1, 1, '超级管理员', '2024-10-09 21:55:33');
INSERT INTO `sys_log_operate` (`id`, `module`, `name`, `req_uri`, `req_method`, `req_params`, `ip`, `user_agent`, `operate_type`, `duration`, `status`, `user_id`, `real_name`, `create_time`) VALUES (2872, '菜单管理', '菜单导航', '/lana/sys/menus/nav', 'GET', NULL, '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36', 1, 0, 1, 1, '超级管理员', '2024-10-09 21:55:44');
COMMIT;

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint DEFAULT NULL COMMENT '上级ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单英文名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单页面地址',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '内容',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '重定向地址',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单请求地址,可不填',
  `sort` int DEFAULT NULL COMMENT '排序',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '显示名称。展示在菜单，标签和面包屑等中',
  `hidden` tinyint DEFAULT NULL COMMENT '是否隐藏菜单，大部分用在无需显示在左侧菜单中的页面，比如详情页',
  `affix` tinyint DEFAULT NULL COMMENT '是否固定，类似首页控制台在标签中是没有关闭按钮的',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '显示图标，建立2级菜单都设置图标，否则菜单折叠都将显示空白',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型：菜单，Iframe，外链，按钮',
  `hiddenBreadcrumb` tinyint DEFAULT NULL COMMENT '是否隐藏面包屑',
  `active` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '左侧菜单的路由地址活动状态，比如打开详情页需要列表页的菜单活动状态',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '颜色值',
  `fullpage` tinyint DEFAULT NULL COMMENT '是否整页打开路由（脱离框架系），例如：fullpage: true',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '静态路由时，所能访问路由的角色，例如：role: ["SA"]',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='菜单';

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (75, NULL, 'home', '/home', 'home', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '首页', NULL, NULL, 'el-icon-home-filled', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:17', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (76, 75, 'dashboard', '/dashboard', 'home', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '控制台', NULL, NULL, 'el-icon-menu', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-10 23:26:08', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (77, 75, 'userCenter', '/usercenter', 'userCenter', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '帐号信息', NULL, NULL, 'el-icon-avatar', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:09:43', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (78, NULL, 'setting', '/setting', 'setting', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '系统设置', NULL, NULL, 'el-icon-setting', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:08:42', 1, '2024-05-09 10:03:51', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (80, 78, 'user', '/setting/user', 'setting/user', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 0, '用户管理', NULL, NULL, 'el-icon-user', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (81, 78, 'role', '/setting/role', 'setting/role', NULL, NULL, NULL, 2, 1, '[超级管理员:admin]', 0, '角色管理', NULL, NULL, 'el-icon-operation', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:06:57', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (82, 78, 'dept', '/setting/dept', 'setting/dept', NULL, NULL, NULL, 3, 1, '[超级管理员:admin]', 0, '部门管理', NULL, NULL, 'sc-icon-organization', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:11', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (83, 78, 'dic', '/setting/dic', 'setting/dic', NULL, NULL, NULL, 4, 1, '[超级管理员:admin]', 0, '字典管理', NULL, NULL, 'el-icon-cellphone', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:28', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (85, 78, 'settingMenu', '/setting/menu', 'setting/menu', NULL, NULL, NULL, 5, 1, '[超级管理员:admin]', 0, '菜单管理', NULL, NULL, 'el-icon-menu', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:08:40', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (105, 78, 'system', '/setting/system', 'setting/system', NULL, NULL, NULL, 1, 1, '[超级管理员:admin]', 1, '系统设置', NULL, NULL, 'el-icon-user', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-03 17:30:09', 1, '2024-05-09 10:04:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (200, 85, 'menus.save', '', '', NULL, 'sys:menus:save', 'api/sys/menus/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:26:27', 1, '2024-05-07 17:26:58', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (201, 85, 'menus.update', '', '', NULL, 'sys:menus:update', 'api/sys/menus/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:27:35', 1, '2024-05-07 17:33:13', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (202, 85, 'menus.delete', '', '', NULL, 'sys:menus:delete', 'api/sys/menus/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:33:36', 1, '2024-05-07 17:35:35', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (203, 85, 'menus.list', '', '', NULL, 'sys:menus:list', 'api/sys/menus/list', NULL, 1, '[超级管理员:admin]', 0, '查看', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-07 17:36:00', 1, '2024-05-07 17:42:25', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (205, 83, 'dict.save', '', '', NULL, 'sys:dict:save', 'api/sys/dict/save', NULL, 1, '[超级管理员:admin]', 0, '字典类型新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (206, 83, 'dict.update', '', '', NULL, 'sys:dict:update', 'api/sys/dict/update', NULL, 1, '[超级管理员:admin]', 0, '字典类型修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:56:12', 1, '2024-05-08 17:59:49', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (207, 83, 'dict.delete', '', '', NULL, 'sys:dict:delete', 'api/sys/dict/delete', NULL, 1, '[超级管理员:admin]', 0, '字典类型删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:57:46', 1, '2024-05-08 17:59:54', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (208, 83, 'dict.list', '', '', NULL, 'sys:dict:list', 'api/sys/dict/list', NULL, 1, '[超级管理员:admin]', 0, '字典类型列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (209, 82, 'org.save', '', '', NULL, 'sys:org:save', 'api/sys/org/save', NULL, 1, '[超级管理员:admin]', 0, '保存', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (210, 82, 'org.update', '', '', NULL, 'sys:org:update', 'api/sys/org/update', NULL, 1, '[超级管理员:admin]', 0, '更新', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (211, 82, 'org.delete', '', '', NULL, 'sys:org:delete', 'api/sys/org/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (212, 82, 'org.list', '', '', NULL, 'sys:org:list', 'api/sys/org/list', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (213, 81, 'role.save', '', '', NULL, 'sys:role:save', 'api/sys/role/save', NULL, 1, '[超级管理员:admin]', 0, '新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (214, 81, 'role.update', '', '', NULL, 'sys:role:update', 'api/sys/role/update', NULL, 1, '[超级管理员:admin]', 0, '修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (215, 81, 'role.delete', '', '', NULL, 'sys:role:delete', 'api/sys/role/delete', NULL, 1, '[超级管理员:admin]', 0, '删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (216, 81, 'role.page', '', '', NULL, 'sys:role:page', 'api/sys/role/page', NULL, 1, '[超级管理员:admin]', 0, '列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (217, 80, 'user.page', '', '', NULL, 'sys:user:page', 'api/sys/user/page', NULL, 1, '[超级管理员:admin]', 0, '保存', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:01:06', 1, '2024-05-08 17:57:05', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (218, 81, 'role.roleLinkMenus', '', '', NULL, 'sys:role:roleLinkMenus', 'api/sys/role/roleLinkMenus', NULL, 1, '[超级管理员:admin]', 0, '权限设置', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (219, 80, 'user.save', '', '', NULL, 'sys:user:save', 'api/sys/user/save', NULL, 1, '[超级管理员:admin]', 0, '新增用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (220, 80, 'user.update', '', '', NULL, 'sys:user:update', 'api/sys/user/update', NULL, 1, '[超级管理员:admin]', 0, '修改用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (221, 80, 'user.delete', '', '', NULL, 'sys:user:delete', 'api/sys/user/delete', NULL, 1, '[超级管理员:admin]', 0, '删除用户', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (222, 80, 'user.resetPassword', '', '', NULL, 'sys:user:password', 'api/sys/user/resetPassword', NULL, 1, '[超级管理员:admin]', 0, '重置默认密码', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (223, 80, 'user.page', '', '', NULL, 'sys:user:password', 'api/sys/user/page', NULL, 1, '[超级管理员:admin]', 0, '用户列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 20:28:01', 1, '2024-05-08 20:28:55', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (224, 83, 'dict.page', '', '', NULL, 'sys:dic:page', 'api/sys/dic/page', NULL, 1, '[超级管理员:admin]', 0, '字典子项列表', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (225, 83, 'dict.save', '', '', NULL, 'sys:dic:save', 'api/sys/dic/save', NULL, 1, '[超级管理员:admin]', 0, '字典子项新增', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (226, 83, 'dict.update', '', '', NULL, 'sys:dic:update', 'api/sys/dic/update', NULL, 1, '[超级管理员:admin]', 0, '字典子项修改', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
INSERT INTO `sys_menus` (`id`, `pid`, `name`, `path`, `component`, `redirect`, `code`, `url`, `sort`, `creator`, `updater_name`, `deleted`, `title`, `hidden`, `affix`, `icon`, `type`, `hiddenBreadcrumb`, `active`, `color`, `fullpage`, `role`, `create_time`, `updater`, `update_time`, `creator_name`) VALUES (227, 83, 'dict.delete', '', '', NULL, 'sys:dic:delete', 'api/sys/dic/delete', NULL, 1, '[超级管理员:admin]', 0, '字典子项删除', NULL, NULL, NULL, 'button', NULL, NULL, NULL, NULL, NULL, '2024-05-08 17:58:35', 1, '2024-05-08 18:00:00', '[超级管理员:admin]');
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '机构名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  `status` tinyint DEFAULT NULL COMMENT '1是0否',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_pid` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='机构管理';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (1, NULL, '鲶鱼科技技术公司', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (2, 1, '研发子公司', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (3, 1, '风投子公司', 2, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-09-30 17:03:33', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (4, 1, '财务子公司', 3, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (5, NULL, '电鳗科技技术公司', 2, 1, '备注备注', 1, 1, '2024-01-10 15:27:38', 1, '2024-07-25 17:45:31', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (6, 5, '研发子公司', 3, 1, '备注备注', 1, 1, '2024-01-10 15:27:38', 1, '2024-07-25 17:45:19', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (7, 5, '风投子公司', 2, 1, '备注备注', 1, 1, '2024-01-10 15:27:38', 1, '2024-07-25 17:45:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (8, 5, '财务子公司', 1, 1, '备注备注', 1, 1, '2024-01-10 15:27:38', 1, '2024-07-25 17:45:23', NULL, '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (9, NULL, '鲤鱼科技技术公司', 3, 1, '备注备注', 1, 1, '2024-04-28 13:33:36', 1, '2024-07-25 17:45:07', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (10, 9, '技术部', 1, 1, '备注备注', 1, 1, '2024-04-28 13:34:18', 1, '2024-07-25 17:45:02', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (11, 10, '财务部', 1, 1, '财务部财务部', 1, 1, '2024-04-28 13:34:41', 1, '2024-04-28 14:34:42', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (12, 2, '研发部', 1, 1, '备注备注', 0, 1, '2024-01-10 15:27:38', 1, '2024-04-04 15:27:50', NULL, NULL);
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (13, 10, '运维部', 1, 1, '', 1, 1, '2024-07-22 10:17:44', 1, '2024-07-25 17:44:58', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_org` (`id`, `pid`, `name`, `sort`, `status`, `remark`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (14, NULL, 'cs', 1, 0, '', 1, 1, '2024-08-05 18:40:09', 1, '2024-08-06 10:42:21', '[超级管理员:admin]', '[超级管理员:admin]');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `data_scope` tinyint DEFAULT NULL COMMENT '数据范围  0：全部数据  1：所在部门及子级可见  2：所在部门可见 3：本人数据 ',
  `org_id` bigint DEFAULT NULL COMMENT '机构ID',
  `sort` int DEFAULT NULL COMMENT '排序',
  `status` bigint DEFAULT '1' COMMENT '是否启用',
  `deleted` tinyint DEFAULT NULL COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_org_id` (`org_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色管理';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (1, '小研发', 'RD', '研发人员', 3, NULL, 7, 1, 1, 1, '2024-01-10 15:30:16', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (2, '技术主管', 'TS', '测试', 1, NULL, 2, 1, 1, 1, '2024-01-10 15:30:31', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (3, '研发组长', 'TL', '开发leader啊', 3, NULL, 3, 1, 1, 1, '2024-01-10 15:31:36', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (4, '小运维', 'OM', '运维人员', 3, NULL, 4, 1, 1, 1, '2024-01-10 15:32:03', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (5, '小产品', 'PM', '是个小产品啊', 3, NULL, 5, 1, 1, 1, '2024-01-10 15:32:51', 1, '2024-07-25 17:38:21', NULL, '[超级管理员:admin]');
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (7, '管理员', 'Administrators', '1', 0, NULL, 1, 1, 0, 1, '2024-05-11 13:29:30', 1, '2024-07-16 15:59:13', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_role` (`id`, `name`, `role_code`, `remark`, `data_scope`, `org_id`, `sort`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (10, '测试', 'cs', '', 2, NULL, 8, 1, 0, 1, '2024-07-16 17:34:26', 1, '2024-07-16 17:34:26', '[超级管理员:admin]', '[超级管理员:admin]');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_menu_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=464 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '填写真实姓名，填写后不允许修改',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像',
  `gender` tinyint DEFAULT NULL COMMENT '性别   0：男   1：女   2：未知',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '个人签名',
  `super_admin` tinyint DEFAULT '0' COMMENT '超级管理员   0：否   1：是',
  `status` tinyint DEFAULT '1' COMMENT '状态  0：停用   1：正常',
  `deleted` tinyint DEFAULT '0' COMMENT '删除标识  0：正常   1：已删除',
  `creator` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `creator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建人，格式：[姓名:账号]',
  `updater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改人，格式：[姓名:账号]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户管理';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `avatar`, `gender`, `email`, `mobile`, `signature`, `super_admin`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (1, 'admin', '{bcrypt}$2a$10$XuBZ06Es71fdsvr8wmjA2uJ.BMoSHNZF.uYEjHNq/gLpQsF8Xnwuu', '超级管理员', 'http://121.5.79.252:9000/dev/20240618/08089446ef247a095eb42885453c7adc_65902.jpeg', 1, 'lyl@163.com', '13245677645', '三月来百草开 盈香满袖万物苏\n虫鸣和着欢笑 心事舒', 1, 1, 0, 10000, '2024-01-10 06:13:37', 1, '2024-10-09 21:55:31', '[超级管理员:admin]', '[超级管理员:admin]');
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `avatar`, `gender`, `email`, `mobile`, `signature`, `super_admin`, `status`, `deleted`, `creator`, `create_time`, `updater`, `update_time`, `creator_name`, `updater_name`) VALUES (2, 'lana', '{bcrypt}$2a$10$1wApb.mfgTderWVl9mof0.xB3gFa1xM0BOZ.3HiFhUpdT4FexU2Oy', 'lana0101', 'http://121.5.79.252:9000/dev/20240903/开发迭代流程_79618.png', 2, 'lyl@163.com', '13245677646', '牛马干活\\韭菜施肥\\蘸料上色', 0, 1, 0, 10000, '2024-07-22 09:49:43', 1, '2024-09-30 17:06:08', '[超级管理员:admin]', '[超级管理员:admin]');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `org_id` bigint DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_post_id` (`org_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户组织关系';

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_org` (`id`, `user_id`, `org_id`) VALUES (42, 1, 1);
INSERT INTO `sys_user_org` (`id`, `user_id`, `org_id`) VALUES (47, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `role_id`, `user_id`) VALUES (34, 7, 1);
INSERT INTO `sys_user_role` (`id`, `role_id`, `user_id`) VALUES (37, 10, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'accessToken',
  `access_token_expire` datetime DEFAULT NULL COMMENT 'accessToken 过期时间',
  `refresh_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'refreshToken',
  `refresh_token_expire` datetime DEFAULT NULL COMMENT 'refreshToken 过期时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_token` (`id`, `user_id`, `access_token`, `access_token_expire`, `refresh_token`, `refresh_token_expire`, `create_time`) VALUES (1, 1, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyODQ4MjE0MiwiZXhwIjoxNzI4NDgyMTg1fQ._QE_C53AgZXZ8Bb2op306m-NKQ7GG8mcpnA7xovB2iM', '2024-10-10 09:55:42', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyODQ4MjE0MiwiZXhwIjoxNzI4NDgzMzUxfQ.R7gUw67FGkL65VGTREh5YjFFL9epol8la25Zl-5RxBc', '2024-10-23 21:55:42', '2024-01-10 14:40:57');
INSERT INTO `sys_user_token` (`id`, `user_id`, `access_token`, `access_token_expire`, `refresh_token`, `refresh_token_expire`, `create_time`) VALUES (2, 2, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzIxMTI0NTk1LCJleHAiOjE3MjExMjQ2Mzh9.A05G5mFeKVfkY3r1iQS9oYsq4Mo4o8re1zeaMR__ab0', '2024-07-17 06:09:55', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzIxMTI0NTk1LCJleHAiOjE3MjExMjU4MDV9.TAqsZBlq22nfrjyQwL-o6GJctuKOy9Or8hlPU5CTdeg', '2024-07-30 18:09:55', '2024-01-10 15:38:18');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
