-- liquibase formatted sql
-- changeset domalchuk:5 failOnError:true logicalFilePath: group.sql
CREATE TABLE IF NOT EXISTS `group`
(
    `id`         BIGINT                      NOT NULL AUTO_INCREMENT,
    `created_by` BIGINT                      NOT NULL,
    `title`      VARCHAR(75)                 NOT NULL,
    `status`     ENUM ('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'INACTIVE',
    `created_at` TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `profile`    TEXT                        NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_group_creator` (`created_by` ASC),
    CONSTRAINT `fk_group_creator`
        FOREIGN KEY (`created_by`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

/*
-- rollback
DROP TABLE group;
*/
