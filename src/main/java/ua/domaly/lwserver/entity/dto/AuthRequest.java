package ua.domaly.lwserver.entity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * DTO to present login form.
 */
@Getter
@Setter
public class AuthRequest {
    @Email
    @NotNull
    private String username;
    @NotNull
    private String password;
}
