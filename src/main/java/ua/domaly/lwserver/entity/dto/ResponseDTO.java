package ua.domaly.lwserver.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO to re-present response.
 */
@Setter
@Getter
public class ResponseDTO {
    private String message;
    private String body;
}
