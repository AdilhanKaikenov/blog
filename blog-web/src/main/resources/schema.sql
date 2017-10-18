DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS blog;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS blog_category_assignment;
DROP TABLE IF EXISTS comment;

CREATE TABLE `user` (
  id INT NOT NULL AUTO_INCREMENT UNIQUE,
  `login` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (id)
);

CREATE TABLE `blog` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `title` VARCHAR(30) NOT NULL,
  `content` VARCHAR(300) NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (id)
);

CREATE TABLE `category` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `blog_category_assignment` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `blog_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `comment` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `blog_id` INT NOT NULL,
  `parent_comment_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `text` VARCHAR(300) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE `blog` ADD CONSTRAINT `blog_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `blog_category_assignment` ADD CONSTRAINT `blog_category_assignment_fk0` FOREIGN KEY (`blog_id`) REFERENCES `blog`(`id`);
ALTER TABLE `blog_category_assignment` ADD CONSTRAINT `blog_category_assignment_fk1` FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk0` FOREIGN KEY (`blog_id`) REFERENCES `blog`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk1` FOREIGN KEY (`parent_comment_id`) REFERENCES `comment`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk2` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);

