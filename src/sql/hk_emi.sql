/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_emi

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-04-04 15:44:41
*/

SET FOREIGN_KEY_CHECKS=0;

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
