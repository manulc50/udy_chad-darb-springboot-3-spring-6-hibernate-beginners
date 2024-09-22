CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NULL,
  `email` VARCHAR(60) NOT NULL,
  `user_id` VARCHAR(50) NOT NULL,
  -- Nota: Usamos un tamaño fijo de 68 caracteres para este campo porque las contraseñas codificadas con BCrypt tienen un tamaño fijo de 60 caracteres y, además,
  -- tenemos que añadir 8 caracteres más para el prefijo "{bcrypt}"
  `pw` CHAR(68) NOT NULL,
  `active` BIT NOT NULL,
  `version` INT,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `members`
VALUES
('John','Bucks','john.bucks@test.com','john','{noop}fun123',1,1),
('Peter','Doe','peter.doe@test.com','peter','{noop}fun123',1,1),
('Mary','Smith','mary.smith@test.com','mary','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1,1),
('Susan','Heard','susan.heard@test.com','susan','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1,1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`user_id`,`role`),
  CONSTRAINT `users_fk` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('john','ROLE_EMPLOYEE'),
('peter','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');
