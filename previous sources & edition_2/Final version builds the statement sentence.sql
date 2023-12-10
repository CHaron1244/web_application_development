SET NAMES utf8mb4; -- Set the character set for database connection to utf8mb4
DROP DATABASE IF EXISTS SHOP;
CREATE DATABASE IF NOT EXISTS shop;
Use shop;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `user_account` VARCHAR(30) NOT NULL PRIMARY KEY COMMENT 'User account',
  `user_password` VARCHAR(30) NOT NULL COMMENT 'User password',
  `user_email` VARCHAR(30) NOT NULL COMMENT 'User email',
  `user_role` INT NOT NULL COMMENT 'User role, 0-normal user, 1-admin'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `Good`;
CREATE TABLE `Good` (
  `good_name` VARCHAR(30) NOT NULL PRIMARY KEY COMMENT 'Good name',
  `good_price` DOUBLE NOT NULL COMMENT 'Good price',
  `good_img` VARCHAR(100) NOT NULL COMMENT 'Good image',
  `good_sale` INT NOT NULL COMMENT 'Good sales volume',
  `good_info` VARCHAR(100) NOT NULL COMMENT 'Good information',
  `good_type` VARCHAR(30) NOT NULL COMMENT 'Good type'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `Cart`;
CREATE TABLE `Cart` (
  `user_account` VARCHAR(30) NOT NULL COMMENT 'User account',
  `good_name` VARCHAR(30) NOT NULL COMMENT 'Good name',
  `good_price` DOUBLE NOT NULL COMMENT 'Good price',
  `good_number` INT NOT NULL COMMENT 'Good quantity',
  PRIMARY KEY (`user_account`, `good_name`),
  FOREIGN KEY(`user_account`) REFERENCES `User`(`user_account`),
  FOREIGN KEY(`good_name`) REFERENCES `Good`(`good_name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `Bill`;
CREATE TABLE `Bill` (
  `user_account` VARCHAR(30) NOT NULL COMMENT 'User account',
  `good_name` VARCHAR(30) NOT NULL COMMENT 'Good name',
  `good_number` INT NOT NULL COMMENT 'Good quantity',
  `order_time` varchar(30) NOT NULL COMMENT 'Order time',
  FOREIGN KEY(`user_account`) REFERENCES `User`(`user_account`),
  FOREIGN KEY(`good_name`) REFERENCES `Good`(`good_name`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `BuyLog`;
CREATE TABLE `BuyLog` (
  `user_account` VARCHAR(30) NOT NULL COMMENT 'User account',
  `good_name` VARCHAR(30) NOT NULL COMMENT 'Good name',
  `good_number` INT NOT NULL COMMENT 'Good quantity',
  `buy_time` varchar(30) NOT NULL COMMENT 'Purchase time',
  FOREIGN KEY(`user_account`) REFERENCES `User`(`user_account`),
  FOREIGN KEY(`good_name`) REFERENCES `Good`(`good_name`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

DROP TABLE IF EXISTS `BrowseLog`;
CREATE TABLE `BrowseLog` (
  `user_account` VARCHAR(30) NOT NULL COMMENT 'User account',
  `good_name` VARCHAR(30) NOT NULL COMMENT 'Good name',
  `browse_time` varchar(30) NOT NULL COMMENT 'Browse time',
   FOREIGN KEY(`user_account`) REFERENCES `User`(`user_account`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
SET FOREIGN_KEY_CHECKS=1; -- Enable foreign key constraint checks
