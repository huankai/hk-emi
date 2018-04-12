/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : hk_dict

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-01-24 13:48:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_base_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_base_code`;
CREATE TABLE `sys_base_code` (
  `id` varchar(32) NOT NULL,
  `base_code` varchar(20) NOT NULL,
  `code_name` varchar(50) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `create_by` varchar(32) NOT NULL,
  `last_update_date` datetime NOT NULL,
  `last_update_by` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Table structure for sys_city
-- ----------------------------
DROP TABLE IF EXISTS `sys_city`;
CREATE TABLE `sys_city` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `code` varchar(50) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `short_name` varchar(50) NOT NULL,
  `english_name` varchar(50) NOT NULL,
  `post_office` varchar(10) NOT NULL,
  `created_by` varchar(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_clild_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_clild_code`;
CREATE TABLE `sys_clild_code` (
  `id` varchar(32) NOT NULL,
  `base_code_id` varchar(32) NOT NULL,
  `child_code` varchar(20) NOT NULL,
  `chde_name` varchar(50) NOT NULL,
  `status` smallint(1) NOT NULL,
  `conditions` varchar(100) NOT NULL,
  `sort` int(5) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `create_by` varchar(32) NOT NULL,
  `last_update_date` datetime NOT NULL,
  `last_update_by` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `base_code_id` (`base_code_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`base_code_id`) REFERENCES `sys_base_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典子表';
SET FOREIGN_KEY_CHECKS=1;
