-- liquibase formatted sql
-- changeset completes:1 failOnError:true
CREATE TABLE `task_user`
(
    `task_id` int NOT NULL,
    `user_id` int NOT NULL,
    KEY       `FKs46ejm4kitq56yd498a3fnr19` (`user_id`),
    KEY       `FKd1fn28rqhh1ku21jx7hcksvh9` (`task_id`),
    CONSTRAINT `FKd1fn28rqhh1ku21jx7hcksvh9` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
    CONSTRAINT `FKs46ejm4kitq56yd498a3fnr19` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- changeset completes:2 failOnError:true
CREATE TABLE `user_task`
(
    `user_id` int NOT NULL,
    `task_id` int NOT NULL,
    KEY       `FKvs34bjkmpbk2e54qlrol3ilt` (`task_id`),
    KEY       `FKr2jik008e3jx6r1fal5e9aq1n` (`user_id`),
    CONSTRAINT `FKr2jik008e3jx6r1fal5e9aq1n` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKvs34bjkmpbk2e54qlrol3ilt` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- changeset completes:3 failOnError:true
INSERT INTO user (active, email, first_name, gradation, last_name, password, role)
VALUES
    (true, 'pr1@gmail.com', 'Тестовий програміст 1', 'TRAINEE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
    (true, 'pr2@gmail.com', 'Тестовий програміст 2', 'JUNIOR', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
    (true, 'pr3@gmail.com', 'Тестовий програміст 3', 'MIDDLE', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER'),
    (true, 'pr4@gmail.com', 'Тестовий програміст 4', 'SENIOR', 'Тест', '$2a$10$UQscJe.mJiN/W8WsgvaqjO4gti49REbVfT46JFyKceM/N4ROerazG', 'USER');

SELECT Id INTO @prId1 FROM user WHERE email = 'pr1@gmail.com';
SELECT Id INTO @prId2 FROM user WHERE email = 'pr2@gmail.com';
SELECT Id INTO @prId3 FROM user WHERE email = 'pr3@gmail.com';
SELECT Id INTO @prId4 FROM user WHERE email = 'pr4@gmail.com';

SELECT Id INTO @qj1 FROM task WHERE question = '1 питання?';
SELECT Id INTO @qj2 FROM task WHERE question = '2 питання?';
SELECT Id INTO @qj3 FROM task WHERE question = '3 питання?';
SELECT Id INTO @qj4 FROM task WHERE question = '4 питання?';
SELECT Id INTO @qj5 FROM task WHERE question = '5 питання?';
SELECT Id INTO @qj6 FROM task WHERE question = '6 питання?';
SELECT Id INTO @qj7 FROM task WHERE question = '7 питання?';
SELECT Id INTO @qj8 FROM task WHERE question = '8 питання?';
SELECT Id INTO @qj9 FROM task WHERE question = '9 питання?';
SELECT Id INTO @qj10 FROM task WHERE question = '10 питання?';
SELECT Id INTO @qj11 FROM task WHERE question = '11 питання?';
SELECT Id INTO @qj12 FROM task WHERE question = '12 питання?';
SELECT Id INTO @qj13 FROM task WHERE question = '13 питання?';
SELECT Id INTO @qj14 FROM task WHERE question = '14 питання?';
SELECT Id INTO @qj15 FROM task WHERE question = '15 питання?';
SELECT Id INTO @qj16 FROM task WHERE question = '16 питання?';
SELECT Id INTO @qj17 FROM task WHERE question = '17 питання?';
SELECT Id INTO @qj18 FROM task WHERE question = '18 питання?';

INSERT INTO user_task (user_id, task_id)
VALUES (@prId4, @qj1),
       (@prId4, @qj2),
       (@prId4, @qj3),
       (@prId4, @qj4),
       (@prId4, @qj5),
       (@prId4, @qj6),
       (@prId4, @qj7),
       (@prId4, @qj8),
       (@prId4, @qj9),
       (@prId4, @qj10),
       (@prId4, @qj11),
       (@prId4, @qj12),
       (@prId4, @qj13),
       (@prId4, @qj14),
       (@prId4, @qj15),
       (@prId4, @qj16),
       (@prId3, @qj1),
       (@prId3, @qj2),
       (@prId3, @qj3),
       (@prId3, @qj4),
       (@prId3, @qj5),
       (@prId3, @qj6),
       (@prId3, @qj7),
       (@prId3, @qj8),
       (@prId3, @qj9),
       (@prId3, @qj10),
       (@prId3, @qj11),
       (@prId2, @qj1),
       (@prId2, @qj2),
       (@prId2, @qj3),
       (@prId2, @qj4),
       (@prId2, @qj5),
       (@prId2, @qj6),
       (@prId1, @qj1),
       (@prId1, @qj2);


INSERT INTO task_user (user_id, task_id)
VALUES (@prId4, @qj1),
       (@prId4, @qj2),
       (@prId4, @qj3),
       (@prId4, @qj4),
       (@prId4, @qj5),
       (@prId4, @qj6),
       (@prId4, @qj7),
       (@prId4, @qj8),
       (@prId4, @qj9),
       (@prId4, @qj10),
       (@prId4, @qj11),
       (@prId4, @qj12),
       (@prId4, @qj13),
       (@prId4, @qj14),
       (@prId4, @qj15),
       (@prId4, @qj16),
       (@prId3, @qj1),
       (@prId3, @qj2),
       (@prId3, @qj3),
       (@prId3, @qj4),
       (@prId3, @qj5),
       (@prId3, @qj6),
       (@prId3, @qj7),
       (@prId3, @qj8),
       (@prId3, @qj9),
       (@prId3, @qj10),
       (@prId3, @qj11),
       (@prId2, @qj1),
       (@prId2, @qj2),
       (@prId2, @qj3),
       (@prId2, @qj4),
       (@prId2, @qj5),
       (@prId2, @qj6),
       (@prId1, @qj1),
       (@prId1, @qj2);

SELECT Id INTO @qcsh1 FROM task WHERE question = '19 питання?';
SELECT Id INTO @qcsh2 FROM task WHERE question = '20 питання?';
SELECT Id INTO @qcsh3 FROM task WHERE question = '21 питання?';
SELECT Id INTO @qcsh4 FROM task WHERE question = '22 питання?';
SELECT Id INTO @qcsh5 FROM task WHERE question = '23 питання?';
SELECT Id INTO @qcsh6 FROM task WHERE question = '24 питання?';
SELECT Id INTO @qcsh7 FROM task WHERE question = '25 питання?';
SELECT Id INTO @qcsh8 FROM task WHERE question = '26 питання?';
SELECT Id INTO @qcsh9 FROM task WHERE question = '27 питання?';
SELECT Id INTO @qcsh10 FROM task WHERE question = '28 питання?';
SELECT Id INTO @qcsh11 FROM task WHERE question = '29 питання?';
SELECT Id INTO @qcsh12 FROM task WHERE question = '30 питання?';
SELECT Id INTO @qcsh13 FROM task WHERE question = '31 питання?';
SELECT Id INTO @qcsh14 FROM task WHERE question = '32 питання?';
SELECT Id INTO @qcsh15 FROM task WHERE question = '33 питання?';
SELECT Id INTO @qcsh16 FROM task WHERE question = '34 питання?';
SELECT Id INTO @qcsh17 FROM task WHERE question = '35 питання?';
SELECT Id INTO @qcsh18 FROM task WHERE question = '36 питання?';

INSERT INTO user_task (user_id, task_id)
VALUES (@prId4, @qcsh1),
       (@prId4, @qcsh2),
       (@prId4, @qcsh3),
       (@prId4, @qcsh4),
       (@prId4, @qcsh5),
       (@prId4, @qcsh6),
       (@prId4, @qcsh7),
       (@prId4, @qcsh8),
       (@prId4, @qcsh9),
       (@prId4, @qcsh10),
       (@prId4, @qcsh11),
       (@prId4, @qcsh12),
       (@prId4, @qcsh13),
       (@prId4, @qcsh14),
       (@prId4, @qcsh15),
       (@prId4, @qcsh16),
       (@prId3, @qcsh1),
       (@prId3, @qcsh2),
       (@prId3, @qcsh3),
       (@prId3, @qcsh4),
       (@prId3, @qcsh5),
       (@prId3, @qcsh6),
       (@prId3, @qcsh7),
       (@prId3, @qcsh8),
       (@prId3, @qcsh9),
       (@prId3, @qcsh10),
       (@prId3, @qcsh11),
       (@prId2, @qcsh1),
       (@prId2, @qcsh2),
       (@prId2, @qcsh3),
       (@prId2, @qcsh4),
       (@prId2, @qcsh5),
       (@prId2, @qcsh6),
       (@prId1, @qcsh1),
       (@prId1, @qcsh2);

INSERT INTO task_user (user_id, task_id)
VALUES (@prId4, @qcsh1),
       (@prId4, @qcsh2),
       (@prId4, @qcsh3),
       (@prId4, @qcsh4),
       (@prId4, @qcsh5),
       (@prId4, @qcsh6),
       (@prId4, @qcsh7),
       (@prId4, @qcsh8),
       (@prId4, @qcsh9),
       (@prId4, @qcsh10),
       (@prId4, @qcsh11),
       (@prId4, @qcsh12),
       (@prId4, @qcsh13),
       (@prId4, @qcsh14),
       (@prId4, @qcsh15),
       (@prId4, @qcsh16),
       (@prId3, @qcsh1),
       (@prId3, @qcsh2),
       (@prId3, @qcsh3),
       (@prId3, @qcsh4),
       (@prId3, @qcsh5),
       (@prId3, @qcsh6),
       (@prId3, @qcsh7),
       (@prId3, @qcsh8),
       (@prId3, @qcsh9),
       (@prId3, @qcsh10),
       (@prId3, @qcsh11),
       (@prId2, @qcsh1),
       (@prId2, @qcsh2),
       (@prId2, @qcsh3),
       (@prId2, @qcsh4),
       (@prId2, @qcsh5),
       (@prId2, @qcsh6),
       (@prId1, @qcsh1),
       (@prId1, @qcsh2);

SELECT Id INTO @qc1 FROM task WHERE question = '39 питання?';
SELECT Id INTO @qc2 FROM task WHERE question = '40 питання?';
SELECT Id INTO @qc3 FROM task WHERE question = '41 питання?';
SELECT Id INTO @qc4 FROM task WHERE question = '42 питання?';
SELECT Id INTO @qc5 FROM task WHERE question = '43 питання?';
SELECT Id INTO @qc6 FROM task WHERE question = '44 питання?';
SELECT Id INTO @qc7 FROM task WHERE question = '45 питання?';
SELECT Id INTO @qc8 FROM task WHERE question = '46 питання?';
SELECT Id INTO @qc9 FROM task WHERE question = '47 питання?';
SELECT Id INTO @qc10 FROM task WHERE question = '48 питання?';
SELECT Id INTO @qc11 FROM task WHERE question = '49 питання?';
SELECT Id INTO @qc12 FROM task WHERE question = '50 питання?';
SELECT Id INTO @qc13 FROM task WHERE question = '51 питання?';
SELECT Id INTO @qc14 FROM task WHERE question = '52 питання?';
SELECT Id INTO @qc15 FROM task WHERE question = '53 питання?';
SELECT Id INTO @qc16 FROM task WHERE question = '54 питання?';
SELECT Id INTO @qc17 FROM task WHERE question = '55 питання?';
SELECT Id INTO @qc18 FROM task WHERE question = '56 питання?';
SELECT Id INTO @qc19 FROM task WHERE question = '57 питання?';

INSERT INTO user_task (user_id, task_id)
VALUES (@prId4, @qc1),
       (@prId4, @qc2),
       (@prId4, @qc3),
       (@prId4, @qc4),
       (@prId4, @qc5),
       (@prId4, @qc6),
       (@prId4, @qc7),
       (@prId4, @qc8),
       (@prId4, @qc9),
       (@prId4, @qc10),
       (@prId4, @qc11),
       (@prId4, @qc12),
       (@prId4, @qc13),
       (@prId4, @qc14),
       (@prId4, @qc15),
       (@prId4, @qc16),
       (@prId3, @qc1),
       (@prId3, @qc2),
       (@prId3, @qc3),
       (@prId3, @qc4),
       (@prId3, @qc5),
       (@prId3, @qc6),
       (@prId3, @qc7),
       (@prId3, @qc8),
       (@prId3, @qc9),
       (@prId3, @qc10),
       (@prId3, @qc11),
       (@prId2, @qc1),
       (@prId2, @qc2),
       (@prId2, @qc3),
       (@prId2, @qc4),
       (@prId2, @qc5),
       (@prId2, @qc6),
       (@prId1, @qc1),
       (@prId1, @qc2);

INSERT INTO task_user (user_id, task_id)
VALUES (@prId4, @qc1),
       (@prId4, @qc2),
       (@prId4, @qc3),
       (@prId4, @qc4),
       (@prId4, @qc5),
       (@prId4, @qc6),
       (@prId4, @qc7),
       (@prId4, @qc8),
       (@prId4, @qc9),
       (@prId4, @qc10),
       (@prId4, @qc11),
       (@prId4, @qc12),
       (@prId4, @qc13),
       (@prId4, @qc14),
       (@prId4, @qc15),
       (@prId4, @qc16),
       (@prId3, @qc1),
       (@prId3, @qc2),
       (@prId3, @qc3),
       (@prId3, @qc4),
       (@prId3, @qc5),
       (@prId3, @qc6),
       (@prId3, @qc7),
       (@prId3, @qc8),
       (@prId3, @qc9),
       (@prId3, @qc10),
       (@prId3, @qc11),
       (@prId2, @qc1),
       (@prId2, @qc2),
       (@prId2, @qc3),
       (@prId2, @qc4),
       (@prId2, @qc5),
       (@prId2, @qc6),
       (@prId1, @qc1),
       (@prId1, @qc2);

SELECT Id INTO @qjs1 FROM task WHERE question = '59 питання?';
SELECT Id INTO @qjs2 FROM task WHERE question = '60 питання?';
SELECT Id INTO @qjs3 FROM task WHERE question = '61 питання?';
SELECT Id INTO @qjs4 FROM task WHERE question = '62 питання?';
SELECT Id INTO @qjs5 FROM task WHERE question = '63 питання?';
SELECT Id INTO @qjs6 FROM task WHERE question = '64 питання?';
SELECT Id INTO @qjs6 FROM task WHERE question = '65 питання?';
SELECT Id INTO @qjs7 FROM task WHERE question = '66 питання?';
SELECT Id INTO @qjs8 FROM task WHERE question = '67 питання?';
SELECT Id INTO @qjs9 FROM task WHERE question = '68 питання?';
SELECT Id INTO @qjs10 FROM task WHERE question = '69 питання?';
SELECT Id INTO @qjs11 FROM task WHERE question = '70 питання?';
SELECT Id INTO @qjs12 FROM task WHERE question = '71 питання?';
SELECT Id INTO @qjs13 FROM task WHERE question = '72 питання?';
SELECT Id INTO @qjs14 FROM task WHERE question = '73 питання?';
SELECT Id INTO @qjs15 FROM task WHERE question = '74 питання?';
SELECT Id INTO @qjs16 FROM task WHERE question = '75 питання?';
SELECT Id INTO @qjs17 FROM task WHERE question = '76 питання?';
SELECT Id INTO @qjs18 FROM task WHERE question = '77 питання?';

INSERT INTO user_task (user_id, task_id)
VALUES (@prId4, @qjs1),
       (@prId4, @qjs2),
       (@prId4, @qjs3),
       (@prId4, @qjs4),
       (@prId4, @qjs5),
       (@prId4, @qjs6),
       (@prId4, @qjs7),
       (@prId4, @qjs8),
       (@prId4, @qjs9),
       (@prId4, @qjs10),
       (@prId4, @qjs11),
       (@prId4, @qjs12),
       (@prId4, @qjs13),
       (@prId4, @qjs14),
       (@prId4, @qjs15),
       (@prId4, @qjs16),
       (@prId3, @qjs1),
       (@prId3, @qjs2),
       (@prId3, @qjs3),
       (@prId3, @qjs4),
       (@prId3, @qjs5),
       (@prId3, @qjs6),
       (@prId3, @qjs7),
       (@prId3, @qjs8),
       (@prId3, @qjs9),
       (@prId3, @qjs10),
       (@prId3, @qjs11),
       (@prId2, @qjs1),
       (@prId2, @qjs2),
       (@prId2, @qjs3),
       (@prId2, @qjs4),
       (@prId2, @qjs5),
       (@prId2, @qjs6),
       (@prId1, @qjs1),
       (@prId1, @qjs2);

INSERT INTO task_user (user_id, task_id)
VALUES (@prId4, @qjs1),
       (@prId4, @qjs2),
       (@prId4, @qjs3),
       (@prId4, @qjs4),
       (@prId4, @qjs5),
       (@prId4, @qjs6),
       (@prId4, @qjs7),
       (@prId4, @qjs8),
       (@prId4, @qjs9),
       (@prId4, @qjs10),
       (@prId4, @qjs11),
       (@prId4, @qjs12),
       (@prId4, @qjs13),
       (@prId4, @qjs14),
       (@prId4, @qjs15),
       (@prId4, @qjs16),
       (@prId3, @qjs1),
       (@prId3, @qjs2),
       (@prId3, @qjs3),
       (@prId3, @qjs4),
       (@prId3, @qjs5),
       (@prId3, @qjs6),
       (@prId3, @qjs7),
       (@prId3, @qjs8),
       (@prId3, @qjs9),
       (@prId3, @qjs10),
       (@prId3, @qjs11),
       (@prId2, @qjs1),
       (@prId2, @qjs2),
       (@prId2, @qjs3),
       (@prId2, @qjs4),
       (@prId2, @qjs5),
       (@prId2, @qjs6),
       (@prId1, @qjs1),
       (@prId1, @qjs2);

/*
-- rollback
DROP TABLE task_user;
DROP TABLE user_task;
*/
