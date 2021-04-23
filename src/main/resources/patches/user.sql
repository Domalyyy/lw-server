-- liquibase formatted sql
-- changeset domalchuk:1 failOnError:true logicalFilePath: user.sql
CREATE TABLE IF NOT EXISTS `user`
(
    `id`         BIGINT                     NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(50)                NOT NULL,
    `last_name`  VARCHAR(50)                NOT NULL,
    `username`   VARCHAR(50)                NOT NULL,
    `email`      VARCHAR(50)                NULL     DEFAULT NULL,
    `password`   VARCHAR(255)               NOT NULL,
    `created_at` TIMESTAMP                  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `intro`      TINYTEXT                   NULL,
    `role`       ENUM ('USER', 'RECRUITER') NOT NULL,
    `rank`       INT                        NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uq_username` (`username` ASC),
    UNIQUE INDEX `uq_email` (`email` ASC)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT
;

/*
-- rollback
DROP TABLE user;
*/
