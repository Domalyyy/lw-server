package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.User;

import java.util.Optional;

/**
 * Service layer for {@link User}.
 */
@Transactional
@Service
public interface UserService {
    /**
     * Method to find a user by email.
     *
     * @param email an email.
     * @return user within optional.
     */
    Optional<User> findByEmail(String email);

    /**
     * Method to create a new user.
     *
     * @param user {@link User}.
     * @return user within optional.
     */
    Optional<User> save(User user);

    Optional<User> update(User user);

    Optional<User> findById(Integer id);
}
