-- 需要 MySQL 5.6.5以上的版本
CREATE DATABASE ssmdemo;
USE ssmdemo;


-- 用户表
CREATE TABLE app_user (
	`user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	`user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
  `login_name` VARCHAR(50) NOT NULL COMMENT '登录名',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_phone` BIGINT COMMENT '手机号',
  `priority` INT COMMENT '优先级',
  PRIMARY KEY (`user_id`),
  KEY `idx_appuser_loginname`(`login_name`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET=UTF8 COMMENT='用户表'


-- 博客信息表
CREATE TABLE blog_info (
  `blog_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `blog_name` VARCHAR(50) NOT NULL COMMENT '博客名',
  `blogger_id` BIGINT NOT NULL COMMENT '博主ID',
  `blogger_name` VARCHAR(50) COMMENT '博主名',
  `lever` INT COMMENT '博客等级',
  `blog_address` VARCHAR(100) COMMENT '博客地址',
  PRIMARY KEY (`blog_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET=UTF8 COMMENT='博客信息表'



-- 插入初始数据
INSERT INTO 
	app_user(`user_name`, `login_name`, `priority`)
VALUES
	('李雷雷', 'lileilei', 0),
	('韩梅梅', 'hanmeimei', 0);