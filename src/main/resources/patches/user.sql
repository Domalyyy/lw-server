-- liquibase formatted sql
-- changeset user:1 failOnError:true
CREATE TABLE `user`
(
    `id`         int    NOT NULL AUTO_INCREMENT,
    `active`     bit(1) NOT NULL,
    `email`      varchar(255) DEFAULT NULL,
    `first_name` varchar(255) DEFAULT NULL,
    `gradation`  varchar(255) DEFAULT NULL,
    `last_name`  varchar(255) DEFAULT NULL,
    `password`   varchar(255) DEFAULT NULL,
    `role`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- changeset user:2 failOnError:true
INSERT INTO user (active, email, first_name, gradation, last_name, password, role)
VALUES (true, 'programmer@gmail.com', 'Програміст', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'recruiter@gmail.com', 'Рекрутер', 'NOT_REQUIRED', 'Тест','$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER');

/*
-- rollback
DROP TABLE user;
*/
