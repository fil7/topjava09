
create database myDB;
use myDB;
grant all on myDB.* to 'admin'@'localhost' identified by 'test';

CREATE TABLE myDB.`meals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateTime` DATETIME,
  `description` varchar(255) DEFAULT NULL,
  `calories` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO meals(dateTime, description, calories) VALUES ('2012-06-18 10:34:09', 'test', '200');
INSERT INTO meals(dateTime, description, calories) VALUES ('2012-06-19 10:34:09', 'record2', '250');
INSERT INTO meals(dateTime, description, calories) VALUES ('2012-06-20 10:34:09', 'record3', '300');
