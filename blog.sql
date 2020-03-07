/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50641
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2020-03-07 21:28:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(512) DEFAULT NULL,
  `content` text,
  `intro` varchar(1024) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `hits` int(10) DEFAULT NULL,
  `allow_comments` varchar(1) DEFAULT NULL,
  `modified` varchar(32) DEFAULT NULL,
  `created` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO article VALUES ('1', '软件工程的就业形势', '梵蒂冈地方个梵蒂冈', '发的广泛地股份', '1', null, '1', null, '2020-02-29 22:59:23');

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(512) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO category VALUES ('1', '22', '21212');
INSERT INTO category VALUES ('2', '123', '1231');

-- ----------------------------
-- Table structure for `category_article`
-- ----------------------------
DROP TABLE IF EXISTS `category_article`;
CREATE TABLE `category_article` (
  `caid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`caid`),
  KEY `aid` (`aid`),
  KEY `cid` (`cid`),
  CONSTRAINT `aid` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cid` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_article
-- ----------------------------
INSERT INTO category_article VALUES ('6', '2', '1');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(128) DEFAULT NULL,
  `content` text,
  `created` varchar(32) DEFAULT NULL,
  `ip` varchar(32) DEFAULT NULL,
  `status` varchar(1) DEFAULT '0',
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `artid` (`aid`),
  CONSTRAINT `artid` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `fl`
-- ----------------------------
DROP TABLE IF EXISTS `fl`;
CREATE TABLE `fl` (
  `keyname` varchar(32) DEFAULT NULL,
  `hash` varchar(128) DEFAULT NULL,
  `upload_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fl
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'admin', '123');
