CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Nota: Éste es el esquema de tablas por defecto que utiliza Spring Security.
--

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NULL,
  `email` VARCHAR(60) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  -- Nota: Usamos un tamaño fijo de 68 caracteres para este campo porque las contraseñas codificadas con BCrypt tienen un tamaño fijo de 60 caracteres y, además,
  -- tenemos que añadir 8 caracteres más para el prefijo "{bcrypt}"
  `password` CHAR(68) NOT NULL,
  `enabled` BIT NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('John','Bucks','john.bucks@test.com','john','{noop}fun123',1),
('Peter','Doe','peter.doe@test.com','peter','{noop}fun123',1),
('Mary','Smith','mary.smith@test.com','mary','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('Susan','Heard','susan.heard@test.com','susan','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`,`authority`),
  CONSTRAINT `users_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_EMPLOYEE'),
('peter','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');
