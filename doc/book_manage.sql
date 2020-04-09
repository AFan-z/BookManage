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

 Date: 09/04/2020 16:56:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '类型',
  `book_num` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图书编号',
  `book_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '书名',
  `publishing_house` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `publication_year` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出版年限',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_id`(`id`) USING BTREE,
  INDEX `fk_book_type`(`type_id`) USING BTREE,
  CONSTRAINT `fk_book_type` FOREIGN KEY (`type_id`) REFERENCES `book_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (4, 6, '9787115514226', '浪潮之巅 第四版', '人民邮电出版社', '2019', 93.20, 20);
INSERT INTO `book` VALUES (5, 6, '9787111641247', '深入理解Java虚拟机：JVM高级特性与最佳实践（第3版）', '机械工业出版社', '2019', 100.60, 20);
INSERT INTO `book` VALUES (6, 6, '9787111636663', 'Java核心技术 卷I 基础知识（原书第11版）', '机械工业出版社', '2019', 96.90, 20);
INSERT INTO `book` VALUES (7, 6, '9787121198854', '高性能MySQL（第3版）', '电子工业出版社', '2013', 83.20, 20);
INSERT INTO `book` VALUES (8, 6, '9787121360398', 'Spring Boot编程思想（核心篇）', '电子工业出版社', '2019', 69.60, 20);
INSERT INTO `book` VALUES (9, 6, '9787302484929', 'Vue.js实战', '清华大学出版社', '2017', 48.90, 20);
INSERT INTO `book` VALUES (10, 6, '9787517062394', '大数据时代的统计学思维：让你从众多数据中找到真相', '水利水电出版社', '2018', 55.10, 20);
INSERT INTO `book` VALUES (11, 3, '9787205096328', '中国艺术精神', '辽宁人民出版社', '2019', 68.30, 20);
INSERT INTO `book` VALUES (12, 3, '9787556122936', '告别洪荒：人类文明的演进', '湖南人民出版社', '2020', 54.80, 20);
INSERT INTO `book` VALUES (13, 3, '9787567549234', '中国古代神话', '华东师范大学出版社', '2017', 33.80, 20);
INSERT INTO `book` VALUES (14, 2, '9787805017259', '卢浮宫', '北京美术摄影出版社', '2015', 782.80, 15);
INSERT INTO `book` VALUES (15, 2, '9787559823243', '西域文化与敦煌艺术（修订本）', '广西师范大学出版社', '2020', 263.50, 15);
INSERT INTO `book` VALUES (16, 2, '9787559826053', '余下只有噪音：聆听20世纪', '广西师范大学出版社', '2020', 188.50, 15);
INSERT INTO `book` VALUES (17, 1, '9787506384476', '苦难辉煌（大字版）', '作家出版社', '2016', 61.60, 20);
INSERT INTO `book` VALUES (18, 1, '9787550035652', '人间告白', '百花洲文艺出版社', '2020', 32.40, 20);
INSERT INTO `book` VALUES (19, 1, '9787535488473', '自在独行 贾平凹的独行世界', '长江文艺出版社', '2016', 32.20, 20);
INSERT INTO `book` VALUES (20, 4, '9787571004095', '风物中国志·密云', '湖南科技出版社', '2020', 42.30, 20);
INSERT INTO `book` VALUES (21, 4, '9787550262614', '李小龙基本中国拳法：自卫的哲学艺术', '北京联合出版公司', '2016', 29.10, 20);
INSERT INTO `book` VALUES (22, 4, '9787229058760', '思维风暴:全世界聪明人都在做的逻辑思维游戏', '重庆出版社', '2013', 5.00, 30);
INSERT INTO `book` VALUES (23, 5, '9787111592105', '巴菲特致股东的信：投资者和公司高管教程（原书第4版）', '机械工业出版社', '2018', 64.40, 20);
INSERT INTO `book` VALUES (24, 5, '9787521703702', '给年轻人的极简金融课：洞悉日常生活现象背后的金融逻辑', '中信出版社', '2019', 34.10, 20);
INSERT INTO `book` VALUES (25, 5, '9787540477745', ' 有钱人和你想的不一样', '湖南文艺出版社', '2017', 31.70, 20);

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (1, '文学');
INSERT INTO `book_type` VALUES (2, '艺术');
INSERT INTO `book_type` VALUES (3, '文化');
INSERT INTO `book_type` VALUES (4, '生活');
INSERT INTO `book_type` VALUES (5, '经管');
INSERT INTO `book_type` VALUES (6, '科技');

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
INSERT INTO `borrow` VALUES (1, 8, 0, '2020-05-03 11:27:37', '2020-03-01 22:53:34', 0);
INSERT INTO `borrow` VALUES (1, 14, 0, '2020-05-09 10:46:50', '2020-04-29 10:46:50', 0);
INSERT INTO `borrow` VALUES (1, 17, 0, '2020-05-09 10:46:58', '2020-04-29 10:46:58', 0);
INSERT INTO `borrow` VALUES (1, 18, 0, '2020-05-09 10:46:35', '2020-04-29 10:46:35', 0);
INSERT INTO `borrow` VALUES (1, 25, 0, '2020-05-09 10:46:42', '2020-04-29 10:46:42', 0);
INSERT INTO `borrow` VALUES (2, 5, 0, '2020-04-01 11:28:15', '2020-04-05 22:53:40', 0);
INSERT INTO `borrow` VALUES (2, 6, 0, '2020-05-08 11:28:31', '2020-04-05 22:53:43', 0);
INSERT INTO `borrow` VALUES (2, 11, 0, '2020-05-09 10:47:17', '2020-04-29 10:47:17', 0);
INSERT INTO `borrow` VALUES (2, 16, 0, '2020-05-09 10:47:13', '2020-04-29 10:47:13', 0);
INSERT INTO `borrow` VALUES (2, 20, 0, '2020-05-09 10:47:31', '2020-04-29 10:47:31', 0);
INSERT INTO `borrow` VALUES (2, 23, 0, '2020-05-09 10:47:06', '2020-04-29 10:47:06', 0);
INSERT INTO `borrow` VALUES (3, 4, 0, '2020-05-15 11:29:04', '2020-04-05 22:53:50', 0);
INSERT INTO `borrow` VALUES (3, 7, 0, '2020-04-26 11:29:19', '2020-04-05 22:53:53', 0);
INSERT INTO `borrow` VALUES (3, 12, 0, '2020-05-09 10:47:57', '2020-04-29 10:47:57', 0);
INSERT INTO `borrow` VALUES (3, 15, 0, '2020-05-09 10:47:45', '2020-04-29 10:47:45', 0);
INSERT INTO `borrow` VALUES (3, 20, 0, '2020-05-09 10:47:49', '2020-04-29 10:47:49', 0);
INSERT INTO `borrow` VALUES (3, 24, 0, '2020-05-09 10:47:39', '2020-04-29 10:47:39', 0);

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operation_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作内容',
  `operation_user` int(11) NULL DEFAULT NULL COMMENT '操作人',
  `operation_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `operation_user_id_fk_1`(`operation_user`) USING BTREE,
  CONSTRAINT `operation_user_id_fk_1` FOREIGN KEY (`operation_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES (1, '登录操作，第1次登录系统', 1, '2020-04-09 12:37:10');
INSERT INTO `operation_log` VALUES (2, '登录操作，第2次登录系统', 1, '2020-04-09 12:44:04');
INSERT INTO `operation_log` VALUES (3, '登录操作，第3次登录系统', 1, '2020-04-09 12:51:26');
INSERT INTO `operation_log` VALUES (4, '新增用户信息，增加的用户工号：2020032304', 1, '2020-04-09 12:52:06');
INSERT INTO `operation_log` VALUES (5, '修改用户信息，修改的用户工号：2020032304用户ID:4', 1, '2020-04-09 12:52:14');
INSERT INTO `operation_log` VALUES (6, '修改个人信息，修改的用户工号：2020032301用户ID:1', 1, '2020-04-09 12:52:35');
INSERT INTO `operation_log` VALUES (7, '修改个人信息，修改的用户工号：2020032301用户ID:1', 1, '2020-04-09 12:52:44');
INSERT INTO `operation_log` VALUES (8, '登录操作，第4次登录系统', 1, '2020-04-09 13:22:37');
INSERT INTO `operation_log` VALUES (9, '登录操作，第5次登录系统', 1, '2020-04-09 13:24:07');
INSERT INTO `operation_log` VALUES (10, '登录操作，第6次登录系统', 1, '2020-04-09 13:26:14');
INSERT INTO `operation_log` VALUES (11, '登录操作，第7次登录系统', 1, '2020-04-09 13:29:04');
INSERT INTO `operation_log` VALUES (12, '登录操作，第8次登录系统', 1, '2020-04-09 13:54:03');
INSERT INTO `operation_log` VALUES (13, '删除图书信息，编号为：9787506384476', 1, '2020-04-09 13:54:31');
INSERT INTO `operation_log` VALUES (14, '登录操作，第9次登录系统', 1, '2020-04-09 14:01:23');
INSERT INTO `operation_log` VALUES (15, '删除图书信息，编号为：9787535488473', 1, '2020-04-09 14:01:38');
INSERT INTO `operation_log` VALUES (16, '登录操作，第10次登录系统', 1, '2020-04-09 14:06:09');
INSERT INTO `operation_log` VALUES (17, '登录操作，第11次登录系统', 1, '2020-04-09 14:25:43');
INSERT INTO `operation_log` VALUES (18, '登录操作，第1次登录系统', 2, '2020-04-09 14:30:04');
INSERT INTO `operation_log` VALUES (19, '登录操作，第2次登录系统', 2, '2020-04-09 14:33:19');
INSERT INTO `operation_log` VALUES (20, '登录操作，第3次登录系统', 2, '2020-04-09 14:40:41');
INSERT INTO `operation_log` VALUES (21, '登录操作，第4次登录系统', 2, '2020-04-09 14:51:18');
INSERT INTO `operation_log` VALUES (22, '登录操作，第5次登录系统', 2, '2020-04-09 14:52:21');
INSERT INTO `operation_log` VALUES (23, '登录操作，第6次登录系统', 2, '2020-04-09 14:58:08');
INSERT INTO `operation_log` VALUES (24, '登录操作，第12次登录系统', 1, '2020-04-09 15:04:28');
INSERT INTO `operation_log` VALUES (25, '登录操作，第13次登录系统', 1, '2020-04-09 15:19:30');
INSERT INTO `operation_log` VALUES (26, '登录操作，第14次登录系统', 1, '2020-04-09 15:28:04');
INSERT INTO `operation_log` VALUES (27, '删除图书信息，编号为：9787111603702', 1, '2020-04-09 15:28:14');
INSERT INTO `operation_log` VALUES (28, '登录操作，第15次登录系统', 1, '2020-04-09 15:31:40');
INSERT INTO `operation_log` VALUES (29, '登录操作，第16次登录系统', 1, '2020-04-09 15:41:45');
INSERT INTO `operation_log` VALUES (30, '登录操作，第17次登录系统', 1, '2020-04-09 15:43:18');
INSERT INTO `operation_log` VALUES (31, '登录操作，第18次登录系统', 1, '2020-04-09 15:44:43');
INSERT INTO `operation_log` VALUES (32, '登录操作，第19次登录系统', 1, '2020-04-09 15:45:49');
INSERT INTO `operation_log` VALUES (33, '登录操作，第7次登录系统', 2, '2020-04-09 15:46:54');
INSERT INTO `operation_log` VALUES (34, '登录操作，第20次登录系统', 1, '2020-04-09 15:51:23');
INSERT INTO `operation_log` VALUES (35, '登录操作，第21次登录系统', 1, '2020-04-09 15:52:20');
INSERT INTO `operation_log` VALUES (36, '登录操作，第22次登录系统', 1, '2020-04-09 15:54:15');
INSERT INTO `operation_log` VALUES (37, '登录操作，第23次登录系统', 1, '2020-04-09 15:55:12');
INSERT INTO `operation_log` VALUES (38, '登录操作，第24次登录系统', 1, '2020-04-09 16:00:32');
INSERT INTO `operation_log` VALUES (39, '修改图书信息，编号为：9787805017259', 1, '2020-04-09 16:02:36');
INSERT INTO `operation_log` VALUES (40, '修改图书信息，编号为：9787805017259', 1, '2020-04-09 16:02:56');
INSERT INTO `operation_log` VALUES (41, '登录操作，第25次登录系统', 1, '2020-04-09 16:03:28');
INSERT INTO `operation_log` VALUES (42, '登录操作，第26次登录系统', 1, '2020-04-09 16:12:23');
INSERT INTO `operation_log` VALUES (43, '登录操作，第27次登录系统', 1, '2020-04-09 16:18:22');
INSERT INTO `operation_log` VALUES (44, '登录操作，第28次登录系统', 1, '2020-04-09 16:23:03');
INSERT INTO `operation_log` VALUES (45, '新增用户信息，增加的用户工号：2020032305', 1, '2020-04-09 16:34:42');
INSERT INTO `operation_log` VALUES (46, '新增用户信息，增加的用户工号：2020032306', 1, '2020-04-09 16:38:36');
INSERT INTO `operation_log` VALUES (47, '新增用户信息，增加的用户工号：1', 1, '2020-04-09 16:56:15');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `permission_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '图书管理', '/fxml/function/bookManage.fxml');
INSERT INTO `permission` VALUES (2, '用户管理', '/fxml/function/userManage.fxml');
INSERT INTO `permission` VALUES (3, '借阅管理', '/fxml/function/borrowManage.fxml');
INSERT INTO `permission` VALUES (4, '个人借阅管理', '/fxml/function/borrowerBorrow.fxml');
INSERT INTO `permission` VALUES (5, '个人中心', '/fxml/function/personalCenter.fxml');
INSERT INTO `permission` VALUES (6, '系统维护', '/fxml/function/systemManage.fxml');

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
-- Records of role_and_permission
-- ----------------------------
INSERT INTO `role_and_permission` VALUES (1, 1);
INSERT INTO `role_and_permission` VALUES (2, 1);
INSERT INTO `role_and_permission` VALUES (1, 2);
INSERT INTO `role_and_permission` VALUES (1, 3);
INSERT INTO `role_and_permission` VALUES (2, 3);
INSERT INTO `role_and_permission` VALUES (1, 4);
INSERT INTO `role_and_permission` VALUES (2, 4);
INSERT INTO `role_and_permission` VALUES (3, 4);
INSERT INTO `role_and_permission` VALUES (1, 5);
INSERT INTO `role_and_permission` VALUES (2, 5);
INSERT INTO `role_and_permission` VALUES (3, 5);
INSERT INTO `role_and_permission` VALUES (1, 6);

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
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `job_num_unique`(`job_num`) USING BTREE,
  INDEX `userInfo_fk_1`(`userinfo_id`) USING BTREE,
  INDEX `role_id_fk_2`(`role_id`) USING BTREE,
  CONSTRAINT `role_id_fk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userInfo_fk_1` FOREIGN KEY (`userinfo_id`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2020032301', 'system', '2020-03-23 20:00:00', 28, 1, 1);
INSERT INTO `user` VALUES (2, '2020032302', 'book', '2020-03-23 20:21:02', 7, 2, 2);
INSERT INTO `user` VALUES (3, '2020032303', 'borrower', '2020-03-23 20:21:32', 0, 3, 3);
INSERT INTO `user` VALUES (4, '2020032304', '123456', '2020-04-09 12:52:06', 0, 4, 2);
INSERT INTO `user` VALUES (5, '2020032305', '123456', '2018-01-01 16:34:42', 0, 5, 3);
INSERT INTO `user` VALUES (6, '2020032306', '123456', '2019-07-01 16:38:36', 0, 6, 1);
INSERT INTO `user` VALUES (7, '2020032307', '123456', '2019-01-09 16:41:51', 0, 7, 2);
INSERT INTO `user` VALUES (8, '1', '1', '2020-04-09 16:56:15', 0, 8, 1);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '张一二', '男', '2020-04-01 00:00:00', '13147258360', '123456@qq.com', '/image/avatar/1.jpg');
INSERT INTO `userinfo` VALUES (2, '李三三', '女', '2020-04-02 20:25:34', '13225836947', '45678945@qq.com', '/image/avatar/2.jpg');
INSERT INTO `userinfo` VALUES (3, '王五六', '男', '2020-04-02 20:26:23', '19314785269', '6936963@qq.com', '/image/avatar/3.jpg');
INSERT INTO `userinfo` VALUES (4, '叶某痛', '男', '2020-04-09 00:00:00', '1231313', '465465564@qq.com', '/image/avatar/2.jpg');
INSERT INTO `userinfo` VALUES (5, '陈某人', '女', '2019-04-12 00:00:00', '1835563641', '15646@qq.com', '/image/avatar/6.jpg');
INSERT INTO `userinfo` VALUES (6, '王某人', '男', '2020-04-09 00:00:00', '1313133131', '2046316@163.com', '/image/avatar/4.jpg');
INSERT INTO `userinfo` VALUES (7, '周某某', '男', '2020-04-09 16:43:20', '12345642', '78d546@qq.com', '/image/avatar/6.jpg');
INSERT INTO `userinfo` VALUES (8, '叶老板', '女', '2020-04-15 00:00:00', '12313132', '13264645@qq.com', '/image/avatar/3.jpg');

-- ----------------------------
-- Procedure structure for get_operation_num
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_operation_num`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_operation_num`(IN p_id int)
BEGIN
			SELECT DATE_FORMAT(operation_time, "%H") AS hours, COUNT(id) AS num
			FROM operation_log
			WHERE TO_DAYS(operation_time) = TO_DAYS(NOW())
			AND operation_user = p_id
			GROUP BY DATE_FORMAT(operation_time, "%H");
	END
;;
delimiter ;

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
