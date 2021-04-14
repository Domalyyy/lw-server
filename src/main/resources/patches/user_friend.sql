-- liquibase formatted sql
-- changeset domalchuk:2 failOnError:true logicalFilePath: user_friend.sql
CREATE TABLE IF NOT EXISTS `user_friend`
(
    `id`         BIGINT                      NOT NULL AUTO_INCREMENT,
    `source_id`  BIGINT                      NOT NULL,
    `target_id`  BIGINT                      NOT NULL,
    `status`     ENUM ('ACTIVE', 'INACTIVE') NOT NULL,
    `created_at` TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_friend_source` (`source_id` ASC),
    CONSTRAINT `fk_friend_source`
        FOREIGN KEY (`source_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `user_friend`
    ADD INDEX `idx_friend_target` (`target_id` ASC);

ALTER TABLE `user_friend`
    ADD CONSTRAINT `fk_friend_target`
        FOREIGN KEY (`target_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `user_friend`
    ADD UNIQUE `uq_friend` (`source_id`, `target_id`);

/*
-- rollback
DROP TABLE user;
*/
