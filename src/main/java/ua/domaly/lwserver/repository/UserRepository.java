package ua.domaly.lwserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.domaly.lwserver.entity.User;

import java.util.Optional;

/**
 * Repository layer for {@link User}.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Method to fina a user by email.
     *
     * @param email an email.
     * @return user within optional.
     */
    Optional<User> findByEmail(String email);
}
