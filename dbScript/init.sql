CREATE TABLE `limitbuy`.`user` (
  `id` BIGINT(20) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `email` VARCHAR(45) NULL,
  `address` VARCHAR(150) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));
