CREATE SCHEMA if not exists library;

CREATE USER if not exists 'library_admin'@'localhost' IDENTIFIED BY 'library_admin_pass';
GRANT ALL privileges on library.* to 'library_admin'@'localhost';
#comment

Drop table if exists  `library`.`library_users`;
CREATE TABLE `library`.`library_users` (
  `uuid` VARCHAR(80) NOT NULL,
  `name` VARCHAR(45) NULL,
  `user_type` TINYINT,
  `loyality_index` INT(2),
   `password` VARCHAR(80),
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
  Drop table if exists  `library`.`books`;
  CREATE TABLE `library`.`books` (
  `uuid` VARCHAR(80) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `publisher` VARCHAR(45),  
  `release_date` INT(5),
  `nr_of_copies` INT(5),
  `copies_left` INT(5),
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
   Drop table if exists  `library`.`authors`;
   CREATE TABLE `library`.`authors` (
  `uuid` VARCHAR(80) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
    Drop table if exists  `library`.`newspapers`;
  CREATE TABLE `library`.`newspapers` (
  `uuid` VARCHAR(80) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `article_title` VARCHAR(45),
  `publisher` VARCHAR(45),  
  `release_date` DATE,
  `nr_of_copies` INT(5),
  `copies_left` INT(5),
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
Drop table if exists  `library`.`magazines`;
  CREATE TABLE `library`.`magazines` (
  `uuid` VARCHAR(80) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `article_title` VARCHAR(45),
  `publisher` VARCHAR(45),  
  `release_date` DATE,
  `nr_of_copies` INT(5),
  `copies_left` INT(5),
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC)); 
  
  Drop table if exists  `library`.`publications_authors`;
  CREATE TABLE `library`.`publications_authors` (
  `publications_uuid` VARCHAR(80) NOT NULL,
  `authors_uuid` VARCHAR(80) NOT NULL, 
PRIMARY KEY (`publications_uuid`,`authors_uuid`)); 

Drop table if exists `library`.`publication_borrowings`;
create table `library`.`publication_borrowings`(
`publications_uuid` VARCHAR(80) NOT NULL,
`uuid` VARCHAR(80) NOT NULL,
`borrowing_date` DATE,
`deadline` DATE,
`returning_date` DATE,
PRIMARY KEY (`publications_uuid`,`uuid`)); 

