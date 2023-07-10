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

DROP TABLE IF EXISTS `setting`;

CREATE TABLE `setting` (
  `setting_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` int(11) Not null,
  `method_length` int(11) DEFAULT NULL,
  `constructor_length` int(11) DEFAULT NULL,
  `number_of_parameters` int(11) DEFAULT NULL,
  `characters_per_line` int(11) DEFAULT NULL,
  `lines_per_file` int(11) DEFAULT NULL,

   PRIMARY KEY (`setting_id`),
   constraint `FK_ACCOUNT`
   foreign key (`account`)
   references `account` (`id`)   ON DELETE CASCADE
   
  )DEFAULT CHARSET=latin1;

INSERT INTO `account` VALUES
(1,'annakat','annaariana','ansns@gmail.com'),
(2,'edwardjoh','edward','edjoh@gmail.com'),
(3,'cian','cianjohn','ciansyd@gmail.com'),
(4,'sydney','sydneyriley','syd@gmail.com');

INSERT INTO `setting` VALUES
(1, 1, 230, 60,9,89,90),
(5, 1, 270, 50,4,54,23),
(2, 2, 200, 50,78,7,56),
(3, 3, 350, 50,89,56,34),
(4, 4, 400, 90,5,6,7);