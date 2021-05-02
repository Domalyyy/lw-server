package ua.domaly.lwserver.entity.dto;

import lombok.Getter;
import lombok.Setter;
import ua.domaly.lwserver.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * DTO to present {@link User} for registration.
 */
@Getter
@Setter
public class UserRegistrationDTO {
    @NotBlank(message = "Please, provide first name")
    private String firstName;
    @NotBlank(message ="Please, provide last name")
    private String lastName;
    @Email(message = "Looks like email is wrong")
    @NotBlank(message = "Please, provide email")
    private String email;
    @NotBlank(message = "Please, provide password")
    private String password;
    @NotBlank(message = "Please, provide role")
    private String role;
}
