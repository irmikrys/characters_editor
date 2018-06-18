DROP DATABASE IF EXISTS soa_game;
CREATE DATABASE soa_game
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE soa_game;

DROP TABLE IF EXISTS categories, typesSets, elements;
DROP TABLE IF EXISTS users, roles, userroles;

-- users

CREATE TABLE users (
  idUser           INTEGER(30)    NOT NULL AUTO_INCREMENT,
  username         VARCHAR(191)   NOT NULL UNIQUE,
  password         VARCHAR(255)   NOT NULL,
  PRIMARY KEY (idUser)
) ENGINE = InnoDB;

CREATE TABLE roles (
  idRole           INTEGER(30)    NOT NULL AUTO_INCREMENT,
  rolename         VARCHAR(191)   NOT NULL UNIQUE,
  PRIMARY KEY (idRole)
) ENGINE = InnoDB;

CREATE TABLE userroles (
  idUser           INTEGER(30)    NOT NULL,
  idRole           INTEGER(30)    NOT NULL,
  PRIMARY KEY (idUser, idRole),
  FOREIGN KEY (idUser) REFERENCES users (idUser) ON DELETE CASCADE,
  FOREIGN KEY (idRole) REFERENCES roles (idRole) ON DELETE CASCADE
) ENGINE = InnoDB;

-- characters

CREATE TABLE typesSets (
  idTypeSet        INTEGER(30)    NOT NULL AUTO_INCREMENT,
  categoryType     VARCHAR(191)   NOT NULL UNIQUE,
  sizeType         VARCHAR(255)   NOT NULL,
  elementType      VARCHAR(191)   NOT NULL UNIQUE,
  elementFortune   VARCHAR(255)   NOT NULL,
  elementProp      VARCHAR(255)   NOT NULL,
  PRIMARY KEY (idTypeSet)
) ENGINE = InnoDB;

CREATE TABLE categories (
  idCategory       INTEGER(30)    NOT NULL AUTO_INCREMENT,
  name             VARCHAR(191)   NOT NULL UNIQUE,
  size             INTEGER(30)    NOT NULL,
  idUser           INTEGER(30)    NOT NULL,
  idTypeSet        INTEGER(30)    NOT NULL,
  PRIMARY KEY (idCategory),
  FOREIGN KEY (idUser) REFERENCES users (idUser),
  FOREIGN KEY (idTypeSet) REFERENCES typesSets (idTypeSet)
) ENGINE = InnoDB;

CREATE TABLE elements (
  idElement        INTEGER(30)    NOT NULL AUTO_INCREMENT,
  name             VARCHAR(255)   NOT NULL,
  fortune          INTEGER(30)    NOT NULL,
  property         INTEGER(30)    NOT NULL,
  power            INTEGER(30)    NOT NULL,
  idCategory       INTEGER(30)    NOT NULL,
  PRIMARY KEY (idElement),
  FOREIGN KEY (idCategory) REFERENCES categories (idCategory) ON DELETE CASCADE
) ENGINE = InnoDB;


-- sample data

-- 'admin' -> 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg='
-- 'qwer' -> '9vLqj0XYoFfJVmoz+ZR02i5camYE1zYSFlDicwxvsKM='
INSERT INTO users (idUser, username, password) VALUES
  (1, 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg='),
  (2, 'user1', '9vLqj0XYoFfJVmoz+ZR02i5camYE1zYSFlDicwxvsKM='),
  (3, 'user2', '9vLqj0XYoFfJVmoz+ZR02i5camYE1zYSFlDicwxvsKM='),
  (4, 'user3', '9vLqj0XYoFfJVmoz+ZR02i5camYE1zYSFlDicwxvsKM=');

INSERT INTO roles (idRole, rolename) VALUES
  (1, 'User'),
  (2, 'Manager');

INSERT INTO userroles (idUser, idRole) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (3, 1),
  (4, 1);


INSERT INTO typesSets (categoryType, sizeType, elementType, elementFortune, elementProp) VALUES
  ('Wood', 'Number of trees', 'Elf', 'Number of arrows', 'Crossbow type');

INSERT INTO categories (name, size, idUser, idTypeSet) VALUES
  ('Foreign Wood', 50, 2, 1),
  ('Black Wood',   71, 2, 1),
  ('Dark Wood',    30, 3, 1),
  ('Random Wood',  83, 4, 1);

INSERT INTO elements (name, fortune, property, power, idCategory) VALUES
  ('Cori',  5, 1, 30, 1),
  ('Cody',  7, 3, 12, 1),
  ('Bonn', 12, 3, 17, 1),
  ('Core',  9, 2, 26, 1),
  ('Doom', 20, 4, 97, 1),

  ('Coco', 12, 3, 29, 2),
  ('Tore',  9, 1, 40, 2),
  ('Memo', 13, 2, 99, 2),

  ('Romp', 12, 3, 70, 3),
  ('Mora',  9, 2, 69, 3),
  ('Kick', 20, 4, 80, 3),

  ('Elf1',  5, 2, 69, 4),
  ('Elf2', 10, 4, 90, 4);
