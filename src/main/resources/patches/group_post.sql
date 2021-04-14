-- liquibase formatted sql
-- changeset domalchuk:7 failOnError:true logicalFilePath: roup_post.sql
CREATE TABLE IF NOT EXISTS `group_post`
(
    `id`         BIGINT    NOT NULL AUTO_INCREMENT,
    `group_id`   BIGINT    NOT NULL,
    `user_id`    BIGINT    NOT NULL,
    `message`    TINYTEXT  NULL     DEFAULT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_gpost_group` (`group_id` ASC),
    CONSTRAINT `fk_gpost_group`
        FOREIGN KEY (`group_id`)
            REFERENCES `group` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `group_post`
    ADD INDEX `idx_gpost_user` (`user_id` ASC);

ALTER TABLE `group_post`
    ADD CONSTRAINT `fk_gpost_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

/*
-- rollback
DROP TABLE group_post;
*/
