DROP DATABASE IF EXISTS RouteWeather;
CREATE DATABASE RouteWeather CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE RouteWeather;

DROP TABLE IF EXISTS route_stats;
CREATE TABLE route_stats(
   id INT(11) AUTO_INCREMENT,
   PRIMARY KEY (id),
   start VARCHAR (255) NOT NULL,
   destination VARCHAR (255) NOT NULL,
   distance VARCHAR (255) NOT NULL,
   max_temp VARCHAR (255) NOT NULL,
   min_temp VARCHAR (255) NOT NULL,
   search_date DATE,
   created DATETIME
);

DELIMITER $$

CREATE TRIGGER searched BEFORE INSERT ON route_stats
FOR EACH ROW BEGIN
SET new.search_date := now();
SET new.search_date := now();
END;

$$

CREATE TRIGGER created BEFORE INSERT ON route_stats
FOR EACH ROW BEGIN
SET new.created := now();
END;
$$

//Hibernate configuration
//jdbc:mysql://localhost:3306/dbname?useUnicode=yes&characterEncoding=UTF-8