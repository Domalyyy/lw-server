package ua.domaly.lwserver.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO to present answer from user.
 */
@Setter
@Getter
public class TaskAnswer {
    @NotNull
    private Integer userId;
    @NotNull
    private List<String> userAnswers;
}
