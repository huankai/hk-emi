/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_emi

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-05-22 17:56:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` char(32) NOT NULL,
  `app_code` varchar(50) NOT NULL COMMENT '应用编号',
  `app_name` varchar(100) NOT NULL COMMENT '应用名称',
  `app_ip` varchar(50) NOT NULL COMMENT '应用id',
  `app_icon` varchar(100) NOT NULL COMMENT 'icon图标',
  `app_port` smallint(5) NOT NULL COMMENT '端口号',
  `app_status` tinyint(1) NOT NULL COMMENT '状态(1:启用,2:禁用)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_code` (`app_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用系统表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('4028c08162b9340f0162b93427c40000', 'HK_EMI', '字典管理系统', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46');
INSERT INTO `sys_app` VALUES ('4028c08162d29e1f0162d29e30a00000', 'hk-weixin-example', '微信测试系统', '127.0.0.1', 'b.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-17 16:00:05', '4028c08162bda8ce0162bda8df6a0000', '2018-04-17 16:00:05');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d5a0000', 'Code0', 'Name0', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d650001', 'Code1', 'Name1', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d650002', 'Code2', 'Name2', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d660003', 'Code3', 'Name3', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d660004', 'Code4', 'Name4', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d670005', 'Code5', 'Name5', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d670006', 'Code6', 'Name6', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d680007', 'Code7', 'Name7', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d690008', 'Code8', 'Name8', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d6a0009', 'Code9', 'Name9', '127.0.0.1', 'a.png', '80', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');

-- ----------------------------
-- Table structure for sys_base_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_base_code`;
CREATE TABLE `sys_base_code` (
  `id` char(32) NOT NULL,
  `base_code` varchar(20) NOT NULL,
  `code_name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_base_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_child_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_child_code`;
CREATE TABLE `sys_child_code` (
  `id` char(32) NOT NULL,
  `base_code_id` char(32) DEFAULT NULL,
  `child_code` varchar(20) NOT NULL,
  `code_name` varchar(50) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `base_code_id` (`base_code_id`),
  CONSTRAINT `base_code_id` FOREIGN KEY (`base_code_id`) REFERENCES `sys_base_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_child_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_city
-- ----------------------------
DROP TABLE IF EXISTS `sys_city`;
CREATE TABLE `sys_city` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL,
  `code` varchar(20) NOT NULL,
  `city_type` tinyint(1) NOT NULL COMMENT '城市类型，1:国家,2:省或直辖市,3:市,4:区或县,5:镇,6:村',
  `full_name` varchar(50) NOT NULL,
  `short_name` varchar(50) NOT NULL,
  `english_name` varchar(100) DEFAULT NULL,
  `post_office` varchar(10) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_city
-- ----------------------------
INSERT INTO `sys_city` VALUES ('4028c08162be57660162be5779cb0000', '4028c08162be57660162be5779cb0000', '1', '1', '中国', '中国', 'China', '1', null, '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 17:16:44', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 17:16:49');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` char(32) NOT NULL,
  `dept_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`dept_id`) USING BTREE,
  KEY `FK_Reference_6` (`role_id`) USING BTREE,
  CONSTRAINT `sys_dept_role_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `sys_dept_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门角色表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL,
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `org_icon` varchar(100) DEFAULT NULL COMMENT '机构图标',
  `responsible_id` char(32) DEFAULT NULL COMMENT '责任人id',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`responsible_id`) USING BTREE,
  KEY `sys_org_parent_id` (`parent_id`),
  CONSTRAINT `sys_org_ibfk_1` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_org_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('402881e662ba5fff0162ba602bff0000', '402881e662ba5fff0162ba602bff0000', '根节点', null, 'a.png', '4028c08162bda8ce0162bda8df6a0000', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:28');

-- ----------------------------
-- Table structure for sys_org_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_dept`;
CREATE TABLE `sys_org_dept` (
  `id` char(32) NOT NULL,
  `org_id` char(32) NOT NULL,
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `parent_id` char(32) NOT NULL COMMENT '上级部门id',
  `responsible_id` char(32) DEFAULT NULL COMMENT '责任人id',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`org_id`) USING BTREE,
  KEY `FK_Reference_14` (`responsible_id`) USING BTREE,
  KEY `sys_org_dept_ibfk_3` (`parent_id`),
  CONSTRAINT `sys_org_dept_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_2` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `sys_org_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构部门表';

-- ----------------------------
-- Records of sys_org_dept
-- ----------------------------
INSERT INTO `sys_org_dept` VALUES ('4028c08162bda84d0162bda85d6b0000', '402881e662ba5fff0162ba602bff0000', '财务部', '4028c08162bda84d0162bda85d6b0000', '4028c08162bda8ce0162bda8df6a0000', null, '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL COMMENT '上级权限id',
  `app_id` char(32) NOT NULL COMMENT '应用名称',
  `permission_code` varchar(20) NOT NULL COMMENT '权限编号',
  `permission_name` varchar(30) NOT NULL COMMENT '权限名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_app_id` (`app_id`) USING BTREE,
  CONSTRAINT `sys_permission_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('4028c081634dc57001634dc84de80001', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_list', '权限管理', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23');
INSERT INTO `sys_permission` VALUES ('4028c081634dc9b001634dd5545a0000', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_create', '添加权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36');
INSERT INTO `sys_permission` VALUES ('4028c081634dc9b001634dd5fb060001', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_edit', '修改权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0119890001', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_delete', '删除权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0122c00005', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_list', '角色管理', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0124df0006', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_create', '添加角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0126b90007', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_edit', '修改角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0128cf0008', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_delete', '删除角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL COMMENT '应用id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `role_code` varchar(30) NOT NULL COMMENT '角色编号',
  `role_status` tinyint(1) NOT NULL COMMENT '角色状态(0:禁用,1:启用)',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_app_id` (`app_id`) USING BTREE,
  CONSTRAINT `sys_role_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('4028c08162d2866a0162d28687770000', '4028c08162b9340f0162b93427c40000', '系统管理员', 'ADMIN', '1', null, '1', '2018-04-17 15:34:15', '1', '2018-04-17 15:34:15');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`permission_id`) USING BTREE,
  KEY `FK_Reference_9` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) NOT NULL COMMENT '主键',
  `org_id` char(32) NOT NULL,
  `dept_id` char(32) NOT NULL COMMENT '部门id',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `password` varchar(100) NOT NULL,
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱虚',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `user_type` tinyint(1) NOT NULL,
  `is_protect` tinyint(1) NOT NULL COMMENT '是否受保护的账号(0,否,1:是)，保护的账号有全部权限',
  `sex` tinyint(1) NOT NULL COMMENT '用户性别(1,男，2：女)',
  `icon_path` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `birth` date DEFAULT NULL COMMENT '生日',
  `privince_id` char(32) DEFAULT NULL COMMENT '省份id',
  `city_id` char(32) DEFAULT NULL COMMENT '市id',
  `user_status` tinyint(1) NOT NULL COMMENT '用户状态(0,禁用,1:启用)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL COMMENT '创建日期',
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone` (`phone`),
  UNIQUE KEY `user_email` (`email`),
  KEY `user_org_id` (`org_id`),
  KEY `user_org_dept_id` (`dept_id`),
  CONSTRAINT `user_org_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `user_org_id` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4028c08162bda8ce0162bda8df6a0000', '402881e662ba5fff0162ba602bff0000', '4028c08162bda84d0162bda85d6b0000', '18820136090', '$2a$10$KgOArE6QpbY2iTQC0WGGS.hP72PQsHpToqbNVEEmUrd5LcEqrbzAG', 'huankai@139.com', '系统管理员', '0', '1', '1', null, '2000-01-01', null, null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44');
INSERT INTO `sys_user` VALUES ('4028c08163872c4e0163872c65e30000', '402881e662ba5fff0162ba602bff0000', '4028c08162bda84d0162bda85d6b0000', '18820132014', '$2a$10$oS/NE1WvZ7LDsPNBPf5t8OmRLuA25tHPRnPS7mkfJfXGb.Y4viNwO', 'xx2@xx.com', '普通用户', '0', '0', '0', null, '2000-01-01', null, null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-22 17:27:04', '4028c08162bda8ce0162bda8df6a0000', '2018-05-22 17:27:04');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_third
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_third`;
CREATE TABLE `sys_user_third` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL COMMENT '用户id',
  `user_third_name` varchar(50) NOT NULL COMMENT '用户名',
  `open_id` varchar(50) NOT NULL,
  `icon_url` varchar(100) DEFAULT NULL COMMENT '头像url',
  `account_type` tinyint(1) NOT NULL COMMENT '账号类型(见数据字典account_type)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `FK_Reference_12` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_third_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- ----------------------------
-- Records of sys_user_third
-- ----------------------------
INSERT INTO `sys_user_third` VALUES ('4028c08162bdb2aa0162bdb2b9ea0000', '4028c08162bda8ce0162bda8df6a0000', 'haha', 'oNvZtv__To1bNI5clj3-oB05OO4U', null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30');
