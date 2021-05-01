package ua.domaly.lwserver.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ua.domaly.lwserver.entity.dto.UserView;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.math.BigInteger;

/**
 * User entity.
 */
@Entity
@Setter
@Getter
public class User {
    public enum Role {
        USER,
        RECRUITER
    }

    public enum Gradation {
        JUNIOR,
        MIDDLE,
        SENIOR,
        EXPERT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Email
    @NonNull
    private String email;

    @NonNull
    private String password;

    @Builder.Default
    private boolean active = true;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gradation gradation;

    /**
     * Method to transform {@link User} to {@link UserView}.
     *
     * @return {@link UserView}.
     */
    public UserView toUserView() {
        return UserView.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gradation(gradation)
                .role(role)
                .build();
    }
}
