/*
 Navicat Premium Data Transfer

 Source Server         : b3xlz3mvmxmvbovqthbz-mysql.services.clever-cloud.com
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : b3xlz3mvmxmvbovqthbz-mysql.services.clever-cloud.com:3306
 Source Schema         : b3xlz3mvmxmvbovqthbz

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 23/03/2023 09:50:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for usersimport
-- ----------------------------
DROP TABLE IF EXISTS `usersimport`;
CREATE TABLE `usersimport`  (
                                `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `createdDate` datetime(0) NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usersimport
-- ----------------------------
INSERT INTO `usersimport` VALUES ('1', 'abraham59', 'rory30@example.com', '975-283-2267', '1', '2019-02-20 14:50:08');
INSERT INTO `usersimport` VALUES ('10', 'abraham59E', 'rory30@example.com', '975-283-2267', '1', '2019-02-20 07:00:00');
INSERT INTO `usersimport` VALUES ('11', 'jerde.tito', 'qpacocha@example.com', '(738)952-6078x1634', '1', '1995-06-13 07:00:00');
INSERT INTO `usersimport` VALUES ('12', 'shanon.gay', 'candelario.grant@example.net', '1-691-238-8463', '0', '1983-02-05 07:00:00');
INSERT INTO `usersimport` VALUES ('13', 'christiana', 'louie85@example.org', '361-461-5922', '0', '2004-08-24 07:00:00');
INSERT INTO `usersimport` VALUES ('14', 'shyann52_e', 'alford91@example.com', '5794709754', '0', '1983-07-12 07:00:00');
INSERT INTO `usersimport` VALUES ('17', 'zstanton', 'stroman.jade@example.com', '(423)483-5096', '0', '2005-09-16 07:00:00');
INSERT INTO `usersimport` VALUES ('18', 'tschultz_e', 'kory.kunze@example.net', '040-373-7213x048', '0', '2018-06-28 07:00:00');
INSERT INTO `usersimport` VALUES ('2', 'jerde.tito', 'qpacocha@example.com', '(738)952-6078x1634', '1', '1995-06-13 12:49:06');
INSERT INTO `usersimport` VALUES ('3', 'shanon.gaylord', 'candelario.grant@example.net', '1-691-238-8463', '', '1983-02-05 06:24:16');
INSERT INTO `usersimport` VALUES ('4', 'christiana60', 'louie85@example.org', '361-461-5922', '', '2004-08-21 08:01:17');
INSERT INTO `usersimport` VALUES ('5', 'shyann52', 'alford91@example.com', '05794709754', '', '1983-07-12 17:53:53');
INSERT INTO `usersimport` VALUES ('6', 'stone48', 'lance40@example.com', '+37(4)0884560459', '', '2005-03-02 09:16:48');
INSERT INTO `usersimport` VALUES ('7', 'laurence64', 'colleen65@example.com', '+81(3)8218226349', '', '2020-08-16 10:59:55');
INSERT INTO `usersimport` VALUES ('8', 'zstanton', 'stroman.jade@example.com', '(423)483-5096', '', '2005-09-16 10:02:53');
INSERT INTO `usersimport` VALUES ('9', 'tschultz', 'kory.kunze@example.net', '040-373-7213x048', '', '2018-06-28 12:15:40');

SET FOREIGN_KEY_CHECKS = 1;
