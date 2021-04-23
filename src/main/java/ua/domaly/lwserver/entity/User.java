package ua.domaly.lwserver.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @Builder.Default
    private Date createdAt = new Date();

    private String intro;

    @Builder.Default
    private boolean active = true;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gradation gradation;
}
