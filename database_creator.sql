CREATE DATABASE `backend` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `comment_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `tex` varchar(255) DEFAULT NULL,
  `data_post` date DEFAULT NULL,
  `id_post` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_post_ibfk_1_idx` (`id_post`),
  KEY `comment_post_ibfk_2` (`id_user`),
  CONSTRAINT `comment_post_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_post_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

CREATE TABLE `complaint` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `date_complaimnt` date DEFAULT NULL,
  `complaint` varchar(255) DEFAULT NULL,
  `id_post` int NOT NULL,
  `id_status_complaint` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `complaint_ibfk_3` (`id_status_complaint`),
  KEY `complaint_ibfk_1` (`id_post`),
  KEY `complaint_ibfk_2` (`id_user`),
  CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `complaint_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `complaint_ibfk_3` FOREIGN KEY (`id_status_complaint`) REFERENCES `status_complaint` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

CREATE TABLE `like_post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_post` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `like_post_ibfk_2` (`id_user`),
  KEY `like_post_ibfk_1` (`id_post`),
  CONSTRAINT `like_post_ibfk_1` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_post_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

CREATE TABLE `hashtag` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `date_post` date DEFAULT NULL,
  `text_post` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

CREATE TABLE `post_hashtag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `id_post` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `id_post` (`id_post`),
  CONSTRAINT `post_hashtag_ibfk_1` FOREIGN KEY (`name`) REFERENCES `hashtag` (`name`),
  CONSTRAINT `post_hashtag_ibfk_2` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`role_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `status_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `status_UNIQUE` (`status_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `subscriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_following` int NOT NULL,
  `id_followers` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_following` (`id_following`),
  KEY `id_followers` (`id_followers`),
  CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`id_following`) REFERENCES `user` (`id`),
  CONSTRAINT `subscriptions_ibfk_2` FOREIGN KEY (`id_followers`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `status_complaint` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_complaint` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`status_complaint`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `fl_name` varchar(255) DEFAULT NULL,
  `password_user` varchar(255) DEFAULT NULL,
  `id_role` int DEFAULT NULL,
  `id_status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `id_role` (`id_role`),
  KEY `id_status` (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

