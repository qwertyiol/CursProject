-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema backend
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema backend
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `backend` DEFAULT CHARACTER SET utf8 ;
USE `backend` ;

-- -----------------------------------------------------
-- Table `backend`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`post` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_user` INT(11) NULL DEFAULT NULL,
  `file_path` VARCHAR(255) NULL DEFAULT NULL,
  `date_post` DATE NULL DEFAULT NULL,
  `text_post` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_user` (`id_user` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 75
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `fl_name` VARCHAR(255) NULL DEFAULT NULL,
  `password_user` VARCHAR(255) NULL DEFAULT NULL,
  `id_role` INT(11) NULL DEFAULT NULL,
  `id_status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_role` (`id_role` ASC) VISIBLE,
  INDEX `id_status` (`id_status` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`comment_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`comment_post` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_user` INT(11) NOT NULL,
  `tex` VARCHAR(255) NULL DEFAULT NULL,
  `data_post` DATE NULL DEFAULT NULL,
  `id_post` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `comment_post_ibfk_1_idx` (`id_post` ASC) VISIBLE,
  INDEX `comment_post_ibfk_2` (`id_user` ASC) VISIBLE,
  CONSTRAINT `comment_post_ibfk_1`
    FOREIGN KEY (`id_post`)
    REFERENCES `backend`.`post` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `comment_post_ibfk_2`
    FOREIGN KEY (`id_user`)
    REFERENCES `backend`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`status_complaint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`status_complaint` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `status_complaint` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`status_complaint` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`complaint`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`complaint` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_user` INT(11) NULL DEFAULT NULL,
  `date_complaimnt` DATE NULL DEFAULT NULL,
  `complaint` VARCHAR(255) NULL DEFAULT NULL,
  `id_post` INT(11) NOT NULL,
  `id_status_complaint` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `complaint_ibfk_3` (`id_status_complaint` ASC) VISIBLE,
  INDEX `complaint_ibfk_1` (`id_post` ASC) VISIBLE,
  INDEX `complaint_ibfk_2` (`id_user` ASC) VISIBLE,
  CONSTRAINT `complaint_ibfk_1`
    FOREIGN KEY (`id_post`)
    REFERENCES `backend`.`post` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `complaint_ibfk_2`
    FOREIGN KEY (`id_user`)
    REFERENCES `backend`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `complaint_ibfk_3`
    FOREIGN KEY (`id_status_complaint`)
    REFERENCES `backend`.`status_complaint` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`hashtag` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`like_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`like_post` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_user` INT(11) NOT NULL,
  `id_post` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `like_post_ibfk_2` (`id_user` ASC) VISIBLE,
  INDEX `like_post_ibfk_1` (`id_post` ASC) VISIBLE,
  CONSTRAINT `like_post_ibfk_1`
    FOREIGN KEY (`id_post`)
    REFERENCES `backend`.`post` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `like_post_ibfk_2`
    FOREIGN KEY (`id_user`)
    REFERENCES `backend`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`post_hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`post_hashtag` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `id_post` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `name` (`name` ASC) VISIBLE,
  INDEX `id_post` (`id_post` ASC) VISIBLE,
  CONSTRAINT `post_hashtag_ibfk_1`
    FOREIGN KEY (`name`)
    REFERENCES `backend`.`hashtag` (`name`),
  CONSTRAINT `post_hashtag_ibfk_2`
    FOREIGN KEY (`id_post`)
    REFERENCES `backend`.`post` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`role_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`role_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_user` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role_user` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`status_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`status_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `status_user` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status_user` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `backend`.`subscriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `backend`.`subscriptions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_following` INT(11) NOT NULL,
  `id_followers` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_following` (`id_following` ASC) VISIBLE,
  INDEX `id_followers` (`id_followers` ASC) VISIBLE,
  CONSTRAINT `subscriptions_ibfk_1`
    FOREIGN KEY (`id_following`)
    REFERENCES `backend`.`user` (`id`),
  CONSTRAINT `subscriptions_ibfk_2`
    FOREIGN KEY (`id_followers`)
    REFERENCES `backend`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
