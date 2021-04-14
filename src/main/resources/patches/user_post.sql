-- liquibase formatted sql
-- changeset domalchuk:8 failOnError:true logicalFilePath: user_post.sql
CREATE TABLE IF NOT EXISTS `user_post`
(
    `id`         BIGINT    NOT NULL AUTO_INCREMENT,
    `user_id`    BIGINT    NOT NULL,
    `sender_id`  BIGINT    NOT NULL,
    `message`    TINYTEXT  NULL     DEFAULT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_upost_user` (`user_id` ASC),
    CONSTRAINT `fk_upost_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `user_post`
    ADD INDEX `idx_upost_sender` (`sender_id` ASC);

ALTER TABLE `user_post`
    ADD CONSTRAINT `fk_upost_sender`
        FOREIGN KEY (`sender_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

/*
-- rollback
DROP TABLE user_post;
*/
