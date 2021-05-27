package ua.domaly.lwserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.MyUserDetails;
import ua.domaly.lwserver.entity.User;
import ua.domaly.lwserver.entity.dto.UserDTO;
import ua.domaly.lwserver.repository.UserRepository;
import ua.domaly.lwserver.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static ua.domaly.lwserver.entity.User.Gradation.JUNIOR;
import static ua.domaly.lwserver.entity.User.Gradation.MIDDLE;
import static ua.domaly.lwserver.entity.User.Gradation.SENIOR;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Value("${gradation.junior:20}")
    private Integer juniorLevel;

    @Value("${gradation.middle:50}")
    private Integer middleLevel;

    @Value("${gradation.senior:100}")
    private Integer seniorLevel;

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Optional<User> findById(final Integer id) {
        return userRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var user = userRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
                );

        return new MyUserDetails(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Optional<User> save(final User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " is already exist");
        }

        return Optional.of(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(final User user) {
        userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User checkAndUpdateGradation(User user) {
        final var size = user.getCompletedTasks().size();

        if (size >= this.seniorLevel) {
            user.setGradation(SENIOR);
        } else if (size >= this.middleLevel) {
            user.setGradation(MIDDLE);
        } else {
            user.setGradation(JUNIOR);
        }

        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNeededCountToUpdate(final User user) {
        final var size = user.getCompletedTasks().size();

        switch (user.getGradation()) {
            case TRAINEE:
                return this.juniorLevel - size;
            case JUNIOR:
                return this.middleLevel - size;
            case MIDDLE:
                return this.seniorLevel - size;
            default:
                return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserDTO> getUsersByProgrammingLanguage(final String programmingLanguage, final Integer userId) {
        final var byId = userRepository.getById(userId);
        final var users = userRepository.findDistinctAllByCompletedTasksProgrammingLanguage(programmingLanguage);
        final List<UserDTO> usersSearch = new ArrayList<>();

        users.forEach(user -> usersSearch.add(
                UserDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .gradation(user.getGradation())
                        .generalCompletedTasks(user.getCompletedTasks().size())
                        .completedTasks((int) user.getCompletedTasks().stream()
                                .filter(task -> task.getProgrammingLanguage().equals(programmingLanguage))
                                .count())
                        .isFriend(user.hasFriend(byId))
                        .isFriendRequested(user.hasRequest(byId))
                        .build()
        ));

        return usersSearch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void sendFriendRequest(final Integer firstUserId, final Integer secondUserId) {
        final var firstUser = userRepository.getById(firstUserId);
        final var secondUser = userRepository.getById(secondUserId);

        firstUser.addFriendRequest(secondUser);

        userRepository.save(firstUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void cancelFriendRequest(final Integer firstUserId, final Integer secondUserId) {
        final var firstUser = userRepository.getById(firstUserId);
        final var secondUser = userRepository.getById(secondUserId);

        firstUser.removeFriendRequest(secondUser);

        userRepository.save(firstUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserDTO> getFriendRequests(final Integer userId) {
        final var currentUser = userRepository.getById(userId);
        final var requests = currentUser.getFriendRequests();

        final List<UserDTO> userDTOS = new ArrayList<>();

        requests.forEach(user -> userDTOS.add(
                UserDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .isFriend(user.hasFriend(currentUser))
                        .isFriendRequested(user.hasRequest(currentUser))
                        .build()
        ));

        return userDTOS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void submitFriendRequests(Integer firstUserId, Integer secondUserId) {
        final var firstUser = userRepository.getById(firstUserId);
        final var secondUser = userRepository.getById(secondUserId);

        secondUser.removeFriendRequest(firstUser);

        firstUser.addFriend(secondUser);
        secondUser.addFriend(firstUser);

        userRepository.save(firstUser);
        userRepository.save(secondUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void declineFriendRequests(Integer firstUserId, Integer secondUserId) {
        final var firstUser = userRepository.getById(firstUserId);
        final var secondUser = userRepository.getById(secondUserId);

        secondUser.removeFriendRequest(firstUser);

        userRepository.save(secondUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<UserDTO> getFriends(Integer userId) {
        final var currentUser = userRepository.getById(userId);
        final var friends = currentUser.getFriends();

        final List<UserDTO> userDTOS = new ArrayList<>();

        friends.forEach(user -> userDTOS.add(
                UserDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getFirstName())
                        .email(user.getEmail())
                        .build()
        ));

        return userDTOS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteFriend(Integer firstUserId, Integer secondUserId) {
        final var firstUser = userRepository.getById(firstUserId);
        final var secondUser = userRepository.getById(secondUserId);

        firstUser.removeFriend(secondUser);
        secondUser.removeFriend(firstUser);

        userRepository.save(firstUser);
        userRepository.save(secondUser);
    }
}
