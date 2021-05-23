-- liquibase formatted sql
-- changeset friend:1 failOnError:true
CREATE TABLE `friends`
(
    `user_id`   int NOT NULL,
    `friend_id` int NOT NULL,
    PRIMARY KEY (`friend_id`, `user_id`),
    KEY         `FKivlhvh7odettdl818ml67apb9` (`user_id`),
    CONSTRAINT `FKivlhvh7odettdl818ml67apb9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKqhlqyd2eb3nmk9hvrfqslw918` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- changeset friend:2 failOnError:true
INSERT INTO user (active, email, first_name, gradation, last_name, password, role)
VALUES (true, 'friend1@gmail.com', 'Тестовий друг 1', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend2@gmail.com', 'Тестовий друг 2', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend3@gmail.com', 'Тестовий друг 3', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend4@gmail.com', 'Тестовий друг 4', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend5@gmail.com', 'Тестовий друг 5', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend6@gmail.com', 'Тестовий друг 6', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend7@gmail.com', 'Тестовий друг 7', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend8@gmail.com', 'Тестовий друг 8', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER'),
       (true, 'friend9@gmail.com', 'Тестовий друг 9', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER'),
       (true, 'friend10@gmail.com', 'Тестовий друг 10', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER'),
       (true, 'friend11@gmail.com', 'Тестовий друг 11', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER');

SELECT Id
INTO @recruiterId
FROM user
WHERE email = 'recruiter@gmail.com';
SELECT Id
INTO @programmerId
FROM user
WHERE email = 'programmer@gmail.com';
SELECT Id
INTO @f1
FROM user
WHERE email = 'friend1@gmail.com';
SELECT Id
INTO @f2
FROM user
WHERE email = 'friend2@gmail.com';
SELECT Id
INTO @f3
FROM user
WHERE email = 'friend3@gmail.com';
SELECT Id
INTO @f4
FROM user
WHERE email = 'friend4@gmail.com';
SELECT Id
INTO @f5
FROM user
WHERE email = 'friend5@gmail.com';
SELECT Id
INTO @f6
FROM user
WHERE email = 'friend6@gmail.com';
SELECT Id
INTO @f7
FROM user
WHERE email = 'friend7@gmail.com';
SELECT Id
INTO @f8
FROM user
WHERE email = 'friend8@gmail.com';
SELECT Id
INTO @f9
FROM user
WHERE email = 'friend9@gmail.com';
SELECT Id
INTO @f10
FROM user
WHERE email = 'friend10@gmail.com';
SELECT Id
INTO @f11
FROM user
WHERE email = 'friend11@gmail.com';

INSERT INTO friends (user_id, friend_id)
VALUES (@recruiterId, @f1),
       (@recruiterId, @f2),
       (@recruiterId, @f3),
       (@recruiterId, @f4),
       (@recruiterId, @f5),
       (@recruiterId, @f6),
       (@recruiterId, @f7),
       (@programmerId, @f8),
       (@programmerId, @f9),
       (@programmerId, @f10),
       (@programmerId, @f11);

INSERT INTO friends (friend_id, user_id)
VALUES (@recruiterId, @f1),
       (@recruiterId, @f2),
       (@recruiterId, @f3),
       (@recruiterId, @f4),
       (@recruiterId, @f5),
       (@recruiterId, @f6),
       (@recruiterId, @f7),
       (@programmerId, @f8),
       (@programmerId, @f9),
       (@programmerId, @f10),
       (@programmerId, @f11);

/*
-- rollback
DROP TABLE friends;
*/
