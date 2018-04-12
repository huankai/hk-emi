/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_emi

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-04-12 18:06:19
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
INSERT INTO `sys_app` VALUES ('4028c08162b9340f0162b93427c40000', 'HK_PMS', '权限管理系统', '127.0.0.1', 'a.png', '80', '1', '1', '2018-04-12 17:33:46', '1', '2018-04-12 17:33:46');

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
INSERT INTO `sys_base_code` VALUES ('4028c08162a353ad0162a356e5b40000', '', '001', '001-description');

-- ----------------------------
-- Table structure for sys_child_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_child_code`;
CREATE TABLE `sys_child_code` (
  `id` char(32) NOT NULL,
  `base_code_id` char(32) NOT NULL,
  `child_code` varchar(20) NOT NULL,
  `code_name` varchar(50) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `full_name` varchar(50) NOT NULL,
  `short_name` varchar(50) NOT NULL,
  `english_name` varchar(100) DEFAULT NULL,
  `post_office` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_city
-- ----------------------------
INSERT INTO `sys_city` VALUES ('4028c081628f91d301628f91deef0000', '4028c081628f91d301628f91deef0000', '1', '中国', '中国', 'China', '1', null);

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
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `org_icon` varchar(100) DEFAULT NULL COMMENT '机构图标',
  `responsible_id` char(32) NOT NULL COMMENT '责任人id',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`responsible_id`) USING BTREE,
  CONSTRAINT `sys_org_ibfk_1` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_dept`;
CREATE TABLE `sys_org_dept` (
  `id` char(32) NOT NULL,
  `org_id` char(32) NOT NULL,
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `parent_id` char(32) NOT NULL COMMENT '上级部门id',
  `responsible_id` char(32) NOT NULL COMMENT '责任人id',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`org_id`) USING BTREE,
  KEY `FK_Reference_14` (`responsible_id`) USING BTREE,
  CONSTRAINT `sys_org_dept_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_2` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构部门表';

-- ----------------------------
-- Records of sys_org_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` char(32) NOT NULL,
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
  `org_id` char(32) NOT NULL COMMENT '机构id',
  `dept_id` char(32) NOT NULL COMMENT '部门id',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱虚',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `is_protect` tinyint(1) NOT NULL COMMENT '是否受保护的账号(0,否,1:是)，保护的账号有全部权限',
  `sex` tinyint(1) NOT NULL COMMENT '用户性别(1,男，2：女)',
  `icon_path` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `birth` date DEFAULT NULL COMMENT '生日',
  `privince_id` char(32) DEFAULT NULL COMMENT '省份id',
  `city_id` char(32) DEFAULT NULL COMMENT '市id',
  `user_status` tinyint(1) NOT NULL COMMENT '用户状态(0,禁用,1:启用)',
  `created_by` char(32) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL COMMENT '创建日期',
  `last_modified_by` char(32) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------

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
  `icon_url` varchar(100) DEFAULT NULL COMMENT '头像url',
  `account_type` tinyint(1) NOT NULL COMMENT '账号类型(见数据字典account_type)',
  `created_by` char(32) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `last_mudified_by` char(32) DEFAULT NULL,
  `last_mudified_date` datetime NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `FK_Reference_12` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_third_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- ----------------------------
-- Records of sys_user_third
-- ----------------------------
