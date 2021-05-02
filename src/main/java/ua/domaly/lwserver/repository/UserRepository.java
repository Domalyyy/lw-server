package ua.domaly.lwserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.domaly.lwserver.entity.User;

import java.util.Optional;

/**
 * Repository layer for {@link User}.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Method to fina a user by email.
     *
     * @param email an email.
     * @return user within optional.
     */
    Optional<User> findByEmail(String email);
}
