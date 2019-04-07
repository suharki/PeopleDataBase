DROP DATABASE IF EXISTS address;

CREATE TABLE `PersonProject`.`address` (
  `id` INT NOT NULL,
  `city` VARCHAR(45) NULL,
  `zip_code` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `home_number` INT NULL,
  `flat_number` INT NULL,
  PRIMARY KEY (`id`));

DROP DATABASE IF EXISTS person;
CREATE TABLE `PersonProject`.`person` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `age` INT NULL,
  `height` INT NULL,
  `weight` DOUBLE NULL,
  `address` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `address_idx` (`address` ASC),
  CONSTRAINT `address`
    FOREIGN KEY (`address`)
    REFERENCES `PersonProject`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);