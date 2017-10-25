DROP TABLE IF EXISTS blog_category_assignment;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS blog;
DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
  id INT NOT NULL AUTO_INCREMENT UNIQUE,
  `login` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `date_of_registration` TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `blog` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `title` VARCHAR(30) NOT NULL,
  `content` VARCHAR(300) NOT NULL,
  `user_id` INT NOT NULL,
  `publication_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `category` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `genre` VARCHAR(30) NOT NULL,
  `added_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `blog_category_assignment` (
  `blog_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  CONSTRAINT blog_category_assignment_id_pk PRIMARY KEY (blog_id, category_id)
);

CREATE TABLE `comment` (
  id int NOT NULL AUTO_INCREMENT UNIQUE,
  `blog_id` INT,
  `category_id` INT,
  `parent_comment_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `text` VARCHAR(300) NOT NULL,
  `comment_date` TIMESTAMP NOT NULL,
  `comment_type` VARCHAR(5) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE `blog` ADD CONSTRAINT `blog_fk0` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
ALTER TABLE `blog_category_assignment` ADD CONSTRAINT `blog_category_assignment_fk0` FOREIGN KEY (`blog_id`) REFERENCES `blog`(`id`);
ALTER TABLE `blog_category_assignment` ADD CONSTRAINT `blog_category_assignment_fk1` FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk0` FOREIGN KEY (`blog_id`) REFERENCES `blog`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk1` FOREIGN KEY (`category_id`) REFERENCES `category`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk2` FOREIGN KEY (`parent_comment_id`) REFERENCES `comment`(`id`);
ALTER TABLE `comment` ADD CONSTRAINT `comment_fk3` FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);
