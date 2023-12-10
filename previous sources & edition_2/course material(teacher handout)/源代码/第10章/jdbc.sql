CREATE DATABASE jdbc;

USE jdbc;

CREATE TABLE users(
		id INT PRIMARY KEY AUTO_INCREMENT,
		NAME VARCHAR(40),
		PASSWORD VARCHAR(40),
		email VARCHAR(60),
		birthday DATE 
)CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO users(NAME,PASSWORD,email,birthday) VALUES('zs','123456','zs@sina.com','1980-12-04');
INSERT INTO users(NAME,PASSWORD,email,birthday) VALUES('lisi','123456','lisi@sina.com','1981-12-04');
INSERT INTO users(NAME,PASSWORD,email,birthday) VALUES('wangwu','123456','wangwu@sina.com','1979-12-04');

CREATE TABLE tb_user (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,-- id主键    
    username VARCHAR(40) NOT NULL,-- 账户名称，设置不为空
    PASSWORD VARCHAR(40) NOT NULL,-- 密码，设置不为空
    NAME VARCHAR(40) DEFAULT NULL,-- 用户真实姓名，默认为空
    gender VARCHAR(20) DEFAULT NULL,-- 用户性别，默认为空
    phonenumber    VARCHAR(30) DEFAULT NULL, -- 用户手机号码，默认为空
    identitycode VARCHAR(30) DEFAULT NULL-- 用户身份证号码，默认为空
);

INSERT INTO tb_user VALUES(1,'张三','123','张三','male','13888888888','110202107075023');