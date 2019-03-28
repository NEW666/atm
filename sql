/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : atm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-03-26 13:07:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminNo` char(9) NOT NULL,
  `adminPawd` char(20) DEFAULT NULL,
  PRIMARY KEY (`adminNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('100', '123');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `transNo` int(11) NOT NULL AUTO_INCREMENT,
  `userNo` char(20) DEFAULT NULL,
  `re_money` float(11,2) DEFAULT NULL,
  `re_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `re_type` char(10) DEFAULT NULL,
  `tf_target` char(20) DEFAULT NULL,
  PRIMARY KEY (`transNo`),
  KEY `userNo` (`userNo`),
  CONSTRAINT `record_ibfk_1` FOREIGN KEY (`userNo`) REFERENCES `user` (`userNo`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('142', '12345', '10000.00', '2018-04-24 10:51:04', '存款', '12345');
INSERT INTO `record` VALUES ('143', '12345', '10000.00', '2018-04-24 10:51:08', '存款', '12345');
INSERT INTO `record` VALUES ('144', '12345', '10000.00', '2018-04-24 10:51:12', '存款', '12345');
INSERT INTO `record` VALUES ('145', '12345', '10000.00', '2018-04-24 10:51:14', '存款', '12345');
INSERT INTO `record` VALUES ('146', '12345', '10000.00', '2018-04-24 10:51:28', '存款', '12345');
INSERT INTO `record` VALUES ('147', '12345', '2000.00', '2018-04-24 11:00:51', '取款', '12345');
INSERT INTO `record` VALUES ('148', '12345', '2000.00', '2018-04-24 11:00:57', '取款', '12345');
INSERT INTO `record` VALUES ('149', '12345', '2000.00', '2018-04-24 11:01:00', '取款', '12345');
INSERT INTO `record` VALUES ('150', '12345', '2000.00', '2018-04-24 11:01:04', '取款', '12345');
INSERT INTO `record` VALUES ('156', '12345', '2000.00', '2018-04-24 11:06:50', '取款', '12345');
INSERT INTO `record` VALUES ('168', '12345', '0.10', '2018-04-24 11:27:49', '转账', '1523458015575');
INSERT INTO `record` VALUES ('169', '12345', '1234.00', '2018-04-24 11:28:32', '转账', '1523458015575');
INSERT INTO `record` VALUES ('170', '12345', '100.00', '2018-04-24 11:29:40', '转账', '3333');
INSERT INTO `record` VALUES ('171', '12345', '100.00', '2018-04-24 11:30:28', '转账', '3333');
INSERT INTO `record` VALUES ('172', '12345', '1.00', '2018-04-24 13:11:23', '转账', '3333');
INSERT INTO `record` VALUES ('173', '12345', '10.00', '2018-04-24 13:11:38', '转账', '3333');
INSERT INTO `record` VALUES ('174', '12345', '10.03', '2018-04-24 13:14:33', '转账', '3333');
INSERT INTO `record` VALUES ('175', '12345', '48000.00', '2018-04-24 13:14:49', '转账', '3333');
INSERT INTO `record` VALUES ('176', '12345', '100.00', '2018-11-02 08:46:34', '存款', '12345');
INSERT INTO `record` VALUES ('177', '12345', '100.00', '2018-11-02 08:46:42', '取款', '12345');
INSERT INTO `record` VALUES ('178', '12345', '100.00', '2018-11-06 15:50:59', '存款', '12345');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userNo` char(20) NOT NULL,
  `userpawd` char(20) DEFAULT NULL,
  `cusNo` char(20) DEFAULT NULL,
  `total` float(20,2) DEFAULT '0.00',
  `be_bank` char(10) DEFAULT NULL,
  `op_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isLose` tinyint(1) DEFAULT NULL,
  `isFrozen` tinyint(1) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`userNo`),
  KEY `cusNo` (`cusNo`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`cusNo`) REFERENCES `user_msg` (`cusNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12345', '123', '123456', '15629.54', '中国银行', '2018-04-11 20:12:59', '0', '0', '0');
INSERT INTO `user` VALUES ('13250', '111', '16506', '74950.02', '中国银行', '2018-04-17 20:42:53', '0', '0', '0');
INSERT INTO `user` VALUES ('1523458015575', '456', '1523458089861', '2129.10', '中国银行', '2018-04-11 22:48:09', '0', '0', '0');
INSERT INTO `user` VALUES ('3333', '123', '654', '103321.03', '建设银行', '2018-04-11 21:18:25', '0', '0', '0');

-- ----------------------------
-- Table structure for user_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_msg`;
CREATE TABLE `user_msg` (
  `cusNo` varchar(20) NOT NULL DEFAULT '',
  `userName` varchar(10) DEFAULT NULL,
  `IDNo` varchar(20) DEFAULT NULL,
  `userPhone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cusNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_msg
-- ----------------------------
INSERT INTO `user_msg` VALUES ('123456', 'yyy', '464313', '131646413');
INSERT INTO `user_msg` VALUES ('1523458089861', 'yyy', '440823199005021213', '13420313501');
INSERT INTO `user_msg` VALUES ('16506', 'xxx', '4160', '4640601');
INSERT INTO `user_msg` VALUES ('654', 'ttt', '41313', '46130312');
