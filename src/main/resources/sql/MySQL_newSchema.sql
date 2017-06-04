-- 需要 MySQL 5.6.5以上的版本
CREATE DATABASE ssmdemo;
USE ssmdemo;


-- 用户表
CREATE TABLE IF NOT EXISTS app_user (
	`user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
	`user_name` VARCHAR(50) NOT NULL COMMENT '用户名',
  `login_name` VARCHAR(50) NOT NULL COMMENT '登录名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_phone` BIGINT COMMENT '手机号',
  `priority` INT COMMENT '优先级',
  PRIMARY KEY (`user_id`),
  KEY `idx_appuser_loginname`(`login_name`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET=UTF8 COMMENT='用户表'
;

ALTER TABLE app_user ADD
(
`password` VARCHAR(100) NOT NULL COMMENT '密码'
)
;

-- 博客信息表
CREATE TABLE IF NOT EXISTS blog_info (
  `blog_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `blog_name` VARCHAR(50) NOT NULL COMMENT '博客名',
  `blogger_id` BIGINT NOT NULL COMMENT '博主ID',
  `blogger_name` VARCHAR(50) COMMENT '博主名',
  `lever` INT COMMENT '博客等级',
  `blog_address` VARCHAR(100) COMMENT '博客地址',
  PRIMARY KEY (`blog_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET=UTF8 COMMENT='博客信息表'
;

-- 版块频道表
CREATE TABLE IF NOT EXISTS app_channel (
  `channel_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '版块ID',
  `channel_name` VARCHAR(50) NOT NULL COMMENT '版块名',
  `is_display` TINYINT NOT NULL COMMENT '是否显示',
  PRIMARY KEY (`channel_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET=UTF8 COMMENT='版块频道表'
;

-- 版块主题表
CREATE TABLE IF NOT EXISTS app_theme (
  `theme_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主题ID',
  `theme_name` VARCHAR(50) NOT NULL COMMENT '主题名',
  `channel_id` BIGINT NOT NULL COMMENT '所属版块',
  `is_display` TINYINT NOT NULL COMMENT '是否显示',
  PRIMARY KEY (`theme_id`)
) ENGINE = INNODB AUTO_INCREMENT = 1000 DEFAULT CHARSET=UTF8 COMMENT='版块主题表'
;

-- 插入初始数据
INSERT INTO 
	app_user(`user_name`, `login_name`, `priority`)
VALUES
	('李雷雷', 'lileilei', 0),
	('韩梅梅', 'hanmeimei', 0)
;

-- 插入初始数据
INSERT INTO
  app_channel(`channel_name`, `is_display`)
VALUES
  ('版块A', 1),
  ('版块B', 1),
  ('版块C', 1),
  ('版块D', 0)
;

-- 插入初始数据
INSERT INTO
  app_theme(`theme_name`, `channel_id`, `is_display`)
VALUES
  ('1000主题A1', 1000, 1),
  ('1000主题A2', 1000, 1),
  ('1001主题A1', 1001, 1),
  ('1001主题B1', 1001, 1),
  ('1002主题C1', 1002, 1),
  ('1000主题D1', 1000, 1)
;
