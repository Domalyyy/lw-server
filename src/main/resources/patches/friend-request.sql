-- liquibase formatted sql
-- changeset friend_request:1 failOnError:true
CREATE TABLE `friends_requests`
(
    `requester_id` int NOT NULL,
    `user_id`      int NOT NULL,
    PRIMARY KEY (`user_id`, `requester_id`),
    KEY            `FKnw1lx3fussv8h58l3bs2hgtkv` (`requester_id`),
    CONSTRAINT `FKdjhkt5drl4nc705hw6gds312a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKnw1lx3fussv8h58l3bs2hgtkv` FOREIGN KEY (`requester_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- changeset friend_request:2 failOnError:true
INSERT INTO user (active, email, first_name, gradation, last_name, password, role)
VALUES (true, 'friend-request1@gmail.com', 'Тестовий друг 1 (реквест)', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend-request2@gmail.com', 'Тестовий друг 2 (реквест)', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
       (true, 'friend-request3@gmail.com', 'Тестовий друг 3 (реквест)', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER'),
       (true, 'friend-request4@gmail.com', 'Тестовий друг 4 (реквест)', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER'),
       (true, 'friend-request5@gmail.com', 'Тестовий друг 5 (реквест)', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER'),
       (true, 'friend-request6@gmail.com', 'Тестовий друг 6 (реквест)', 'NOT_REQUIRED', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'RECRUITER');

SELECT Id
INTO @recruiterId
FROM user
WHERE email = 'recruiter@gmail.com';
SELECT Id
INTO @programmerId
FROM user
WHERE email = 'programmer@gmail.com';
SELECT Id
INTO @fr1
FROM user
WHERE email = 'friend-request1@gmail.com';
SELECT Id
INTO @fr2
FROM user
WHERE email = 'friend-request2@gmail.com';
SELECT Id
INTO @fr3
FROM user
WHERE email = 'friend-request3@gmail.com';
SELECT Id
INTO @fr4
FROM user
WHERE email = 'friend-request4@gmail.com';
SELECT Id
INTO @fr5
FROM user
WHERE email = 'friend-request5@gmail.com';
SELECT Id
INTO @fr6
FROM user
WHERE email = 'friend-request6@gmail.com';

INSERT INTO friends_requests (user_id, requester_id)
VALUES (@recruiterId, @fr1),
       (@recruiterId, @fr2),
       (@programmerId, @fr3),
       (@programmerId, @fr4),
       (@programmerId, @fr5),
       (@programmerId, @fr6);

/*
-- rollback
DROP TABLE friends_requests;
*/
