-- liquibase formatted sql
-- changeset domalchuk:4 failOnError:true logicalFilePath: task.sql
CREATE TABLE IF NOT EXISTS `task`
(
    `id`                   BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`              BIGINT       NOT NULL,
    `programming_language` ENUM ('JAVA', 'JS', 'C', 'C++', 'C#', 'PYTHON'),
    `question`             varchar(255) NULL DEFAULT NULL,
    `wrong_answer`         varchar(255) NULL DEFAULT NULL,
    `correct_answer`       varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    INDEX `idx_user` (`user_id` ASC),
    CONSTRAINT `fk_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
);

/*
-- rollback
DROP TABLE task;
*/
