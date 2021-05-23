-- liquibase formatted sql
-- changeset message:1 failOnError:true
CREATE TABLE `message`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `body`         varchar(255) DEFAULT NULL,
    `posted`       datetime(6) DEFAULT NULL,
    `recipient_id` int          DEFAULT NULL,
    `sender_id`    int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `FKiup8wew331d92o7u3k8d918o3` (`recipient_id`),
    KEY            `FKcnj2qaf5yc36v2f90jw2ipl9b` (`sender_id`),
    CONSTRAINT `FKcnj2qaf5yc36v2f90jw2ipl9b` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKiup8wew331d92o7u3k8d918o3` FOREIGN KEY (`recipient_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- changeset message:2 failOnError:true
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

INSERT INTO message (body, posted, recipient_id, sender_id)
VALUES ('Привіт, маю для тебе класну вакансію, глянеш?', '2021-05-23 12:54:26.355000', @programmerId, @recruiterId),
       ('Привіт, з радістю гляну, надсилай', '2021-05-23 13:54:26.355000', @recruiterId, @programmerId),
       ('Круто, лови: опис вакансії', '2021-05-23 14:54:26.355000', @programmerId, @recruiterId),
       ('Привіт, маю для тебе класну вакансію, глянеш', '2021-05-23 12:54:26.355000', @f1, @recruiterId),
       ('Привіт, так', '2021-05-23 12:55:26.355000', @recruiterId, @f1);

/*
-- rollback
DROP TABLE message;
*/
