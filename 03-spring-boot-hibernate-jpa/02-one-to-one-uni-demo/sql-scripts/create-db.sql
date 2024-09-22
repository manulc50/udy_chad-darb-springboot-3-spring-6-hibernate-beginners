DROP SCHEMA IF EXISTS `hb-01-one-to-one`;

CREATE SCHEMA `hb-01-one-to-one`;

use `hb-01-one-to-one`;

CREATE TABLE `instructor_details` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `youtube_channel` VARCHAR(128),
  `hobby` VARCHAR(45)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `instructors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45),
  `email` VARCHAR(45) NOT NULL,
  `instructor_detail_id` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`) REFERENCES `instructor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
