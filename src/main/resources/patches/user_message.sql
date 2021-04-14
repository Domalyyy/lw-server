-- liquibase formatted sql
-- changeset domalchuk:3 failOnError:true logicalFilePath: user_message.sql
CREATE TABLE IF NOT EXISTS `user_message`
(
    `id`         BIGINT    NOT NULL AUTO_INCREMENT,
    `source_id`  BIGINT    NOT NULL,
    `targetId`   BIGINT    NOT NULL,
    `message`    TINYTEXT  NULL     DEFAULT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_umessage_source` (`source_id` ASC),
    CONSTRAINT `fk_umessage_source`
        FOREIGN KEY (`source_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `user_message`
    ADD INDEX `idx_umessage_target` (`targetId` ASC);

ALTER TABLE `user_message`
    ADD CONSTRAINT `fk_umessage_target`
        FOREIGN KEY (`targetId`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

/*
-- rollback
DROP TABLE user_message;
*/
