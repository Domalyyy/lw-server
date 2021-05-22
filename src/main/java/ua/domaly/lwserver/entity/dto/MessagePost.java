package ua.domaly.lwserver.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class MessagePost {
    @NotNull
    private Integer sender;
    @NotNull
    private Integer recipient;
    @NotEmpty
    @Size(min = 1, max = 1000)
    private String body;
}
