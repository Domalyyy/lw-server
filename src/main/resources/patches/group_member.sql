-- liquibase formatted sql
-- changeset domalchuk:6 failOnError:true logicalFilePath: group_member.sql
CREATE TABLE IF NOT EXISTS `group_member`
(
    `id`         BIGINT                 NOT NULL AUTO_INCREMENT,
    `group_id`   BIGINT                 NOT NULL,
    `user_id`    BIGINT                 NOT NULL,
    `type`       ENUM ('ADMIN', 'USER') NOT NULL,
    `created_at` TIMESTAMP              NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `idx_member_group` (`group_id` ASC),
    CONSTRAINT `fk_member_group`
        FOREIGN KEY (`group_id`)
            REFERENCES `group` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE `group_member`
    ADD INDEX `idx_member_user` (`user_id` ASC);

ALTER TABLE `group_member`
    ADD CONSTRAINT `fk_member_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `group_member`
    ADD UNIQUE `uq_friend` (`group_id`, `user_id`);

/*
-- rollback
DROP TABLE group_member;
*/
