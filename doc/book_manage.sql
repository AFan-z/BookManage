/*
 Navicat Premium Data Transfer

 Source Server         : javaFX
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : javafx

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 05/04/2020 22:55:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书编号',
  `book_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书名',
  `publishing_house` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `publication_year` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版年限',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '9787115373557', '数学之美(第二版)', '人民邮电出版社', '2014', 24.50, 20);
INSERT INTO `book` VALUES (2, '9787115472588', '鸟哥的Linux私房菜 基础学习篇 第四版', '人民邮电出版社', '2018', 59.00, 20);
INSERT INTO `book` VALUES (3, '9787111603702', '利用Python进行数据分析（原书第2版）', '机械工业出版社', '2018', 88.60, 20);
INSERT INTO `book` VALUES (4, '9787115514226', '浪潮之巅 第四版', '人民邮电出版社', '2019', 93.20, 20);
INSERT INTO `book` VALUES (5, '9787111641247', '深入理解Java虚拟机：JVM高级特性与最佳实践（第3版）', '机械工业出版社', '2019', 100.60, 20);
INSERT INTO `book` VALUES (6, '9787111636663', 'Java核心技术 卷I 基础知识（原书第11版）', '机械工业出版社', '2019', 96.90, 20);
INSERT INTO `book` VALUES (7, '9787121198854', '高性能MySQL（第3版）', '电子工业出版社', '2013', 83.20, 20);
INSERT INTO `book` VALUES (8, '9787121360398', 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2019', 69.60, 20);
INSERT INTO `book` VALUES (9, '9787302484929', 'Vue.js实战', '清华大学出版社', '2017', 48.90, 20);
INSERT INTO `book` VALUES (10, '9787517062394', '大数据时代的统计学思维：让你从众多数据中找到真相', '水利水电出版社', '2018', 55.10, 20);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `book_id` int(11) NOT NULL COMMENT '图书编号',
  `is_return` int(2) NULL DEFAULT 0 COMMENT '是否归还',
  `return_time` datetime(0) NULL DEFAULT NULL COMMENT '归还日期',
  `renew_time` datetime(0) NULL DEFAULT NULL COMMENT '续借时间',
  `renew_num` int(11) NULL DEFAULT 0 COMMENT '续借次数',
  PRIMARY KEY (`user_id`, `book_id`) USING BTREE,
  INDEX `fk_book_id`(`book_id`) USING BTREE,
  CONSTRAINT `fk_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (1, 1, 0, '2020-05-03 11:17:10', '2020-03-01 22:53:26', 0);
INSERT INTO `borrow` VALUES (1, 3, 0, '2020-05-01 11:27:15', '2020-03-01 22:53:29', 0);
INSERT INTO `borrow` VALUES (1, 8, 0, '2020-05-03 11:27:37', '2020-03-01 22:53:34', 0);
INSERT INTO `borrow` VALUES (2, 2, 0, '2020-05-09 11:28:03', '2020-03-01 22:53:37', 0);
INSERT INTO `borrow` VALUES (2, 5, 0, '2020-05-16 11:28:15', '2020-04-05 22:53:40', 0);
INSERT INTO `borrow` VALUES (2, 6, 0, '2020-05-08 11:28:31', '2020-04-05 22:53:43', 0);
INSERT INTO `borrow` VALUES (3, 3, 0, '2020-05-10 11:28:43', '2020-04-05 22:53:47', 0);
INSERT INTO `borrow` VALUES (3, 4, 0, '2020-05-15 11:29:04', '2020-04-05 22:53:50', 0);
INSERT INTO `borrow` VALUES (3, 7, 0, '2020-04-26 11:29:19', '2020-04-05 22:53:53', 0);

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operation_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `operation_user` int(11) NULL DEFAULT NULL COMMENT '操作人',
  `operation_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `operation_user_id_fk_1`(`operation_user`) USING BTREE,
  CONSTRAINT `operation_user_id_fk_1` FOREIGN KEY (`operation_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `permission_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `role_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'SYSTEM_ADMIN', '系统管理员');
INSERT INTO `role` VALUES (2, 'BOOK_ADMIN', '图书管理员');
INSERT INTO `role` VALUES (3, 'BORROWER', '借阅者');

-- ----------------------------
-- Table structure for role_and_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_and_permission`;
CREATE TABLE `role_and_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `role_and_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_and_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号（工号）',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `login_num` int(11) NULL DEFAULT 0 COMMENT '登录次数',
  `userinfo_id` int(11) NULL DEFAULT NULL COMMENT '用户信息id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userInfo_fk_1`(`userinfo_id`) USING BTREE,
  CONSTRAINT `userInfo_fk_1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2020032301', 'system', '2020-03-23 20:00:00', 0, 1);
INSERT INTO `user` VALUES (2, '2020032302', 'book', '2020-03-23 20:21:02', 0, 2);
INSERT INTO `user` VALUES (3, '2020032303', 'borrower', '2020-03-23 20:21:32', 0, 3);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `gender` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `employment_year` datetime(0) NULL DEFAULT NULL COMMENT '入职年份',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `email` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色表id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id_fk`(`role_id`) USING BTREE,
  CONSTRAINT `role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '张一二', '男', '2020-04-01 20:23:11', '13147258369', '123456@qq.com', '/image/avatar/1.jpg', 1);
INSERT INTO `userinfo` VALUES (2, '李三三', '女', '2020-04-02 20:25:34', '13225836947', '45678945@qq.com', '/image/avatar/2.jpg', 2);
INSERT INTO `userinfo` VALUES (3, '王五六', '男', '2020-04-02 20:26:23', '19314785269', '6936963@qq.com', '/image/avatar/3.jpg', 3);

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `userinfo_del`;
delimiter ;;
CREATE TRIGGER `userinfo_del` AFTER DELETE ON `user` FOR EACH ROW BEGIN
DELETE FROM userinfo WHERE id = old.userinfo_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
