package ua.domaly.lwserver.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.domaly.lwserver.entity.User;

/**
 * DTO to re-present {@link User}.
 */
@Getter
@Setter
@Builder
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private User.Gradation gradation;
    private Integer generalCompletedTasks;
    private Integer completedTasks;
    private boolean isFriend;
    private boolean isFriendRequested;
}
