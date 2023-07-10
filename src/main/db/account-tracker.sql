CREATE DATABASE  IF NOT EXISTS `hb_account_tracker`;
USE `hb_account_tracker`;

--
-- Table structure for table `account`
--
DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  
  UNIQUE (`username`),
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `settings`;

CREATE TABLE `settings` (
  `setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11),
  `method_length` int(11) DEFAULT NULL,
  `constructor_length` int(11) DEFAULT NULL,

   PRIMARY KEY (`setting_id`),
   constraint `FK_ACCOUNT`
   foreign key (`account`)
   references `account` (`id`)
  )DEFAULT CHARSET=latin1;

INSERT INTO `account` VALUES
(1,'annakat','annaerariana','ansns@gmail.com'),
(2,'edwardjoh','edward','edjoh@gmail.com'),
(3,'cian','cianjohn','ciansyd@gmail.com'),
(4,'sydney','sydneyriley','syd@gmail.com');

INSERT INTO `settings` VALUES
(1, 1, 230, 60),
(5, 1, 270, 50),

(2, 2, 200, 50),
(3, 3, 350, 50),
(4, 4, 400, 90);