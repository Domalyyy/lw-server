package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import ua.domaly.lwserver.entity.User;

import java.util.Optional;

/**
 * Service layer for {@link User}.
 */
@Service
public interface UserService {
    /**
     * Method to fina a user by email.
     *
     * @param email an email.
     * @return user within optional.
     */
    Optional<User> findByEmail(String email);
}
