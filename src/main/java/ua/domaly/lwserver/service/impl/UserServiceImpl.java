package ua.domaly.lwserver.service.impl;

import lombok.RequiredArgsConstructor;
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

/**
 * {@inheritDoc}
 */
@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

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

    @Override
    public Optional<User> update(final User user) {
        return Optional.of(userRepository.save(user));
    }
}
