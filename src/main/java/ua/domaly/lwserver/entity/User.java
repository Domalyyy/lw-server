package ua.domaly.lwserver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.util.List;

/**
 * User entity.
 */
@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private Gradation gradation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> completedTasks;

    public void addCompletedTask(final Task task) {
        if (!completedTasks.contains(task)) {
            completedTasks.add(task);
        }
    }
}
