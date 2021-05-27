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
import java.util.HashSet;
import java.util.Set;

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
        NOT_REQUIRED,
        TRAINEE,
        JUNIOR,
        MIDDLE,
        SENIOR
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
    private Set<Task> completedTasks = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends_requests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "requester_id"))
    private Set<User> friendRequests = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends_requests",
            joinColumns = @JoinColumn(name = "requester_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> friendRequestsOf = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friend_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> friendOf = new HashSet<>();

    /**
     * Method to add task that was passed to user.
     *
     * @param task {@link Task}.
     */
    public void addCompletedTask(final Task task) {
        completedTasks.add(task);
    }

    /**
     * Method to check friend request.
     *
     * @param requester the request.
     * @return true/false.
     */
    public boolean hasRequest(User requester) {
        return friendRequests.contains(requester);
    }

    /**
     * Method to add friend request.
     *
     * @param friend an user.
     */
    public void addFriendRequest(User friend) {
        friendRequests.add(friend);
    }

    /**
     * Method to remove friend request.
     *
     * @param friend an user.
     */
    public void removeFriendRequest(User friend) {
        friendRequests.remove(friend);
    }

    /**
     * Method to check friend.
     *
     * @param friend an user.
     * @return true/false.
     */
    public boolean hasFriend(User friend) {
        return friends.contains(friend);
    }

    /**
     * Method to add friend.
     *
     * @param friend an user.
     */
    public void addFriend(User friend) {
        friends.add(friend);
    }

    /**
     * Method to remove friend.
     *
     * @param friend an user.
     */
    public void removeFriend(User friend) {
        friends.remove(friend);
    }
}
