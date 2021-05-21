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
import ua.domaly.lwserver.repository.UserRepository;
import ua.domaly.lwserver.service.UserService;

import java.util.Optional;

import static java.lang.String.format;
import static ua.domaly.lwserver.entity.User.Gradation.JUNIOR;
import static ua.domaly.lwserver.entity.User.Gradation.MIDDLE;
import static ua.domaly.lwserver.entity.User.Gradation.SENIOR;

/**
 * {@inheritDoc}
 */
@Transactional
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
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findById(final Integer id) {
        return userRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    public Optional<User> update(final User user) {
        return Optional.of(userRepository.save(user));
    }

    /**
     * {@inheritDoc}
     */
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
    public Integer getNeededCountToUpdate(final User user) {
        final var size = user.getCompletedTasks().size();

        switch (user.getGradation()) {
            case JUNIOR:
                return this.middleLevel - size;
            case MIDDLE:
                return this.seniorLevel - size;
            default:
                return 0;
        }
    }
}
