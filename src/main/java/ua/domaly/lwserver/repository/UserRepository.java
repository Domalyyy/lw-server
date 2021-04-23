package ua.domaly.lwserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.domaly.lwserver.entity.User;

import java.math.BigInteger;
import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Repository
public interface UserRepository extends CrudRepository<User, BigInteger> {
    /**
     * Method to fina a user by email.
     *
     * @param email an email.
     * @return user within optional.
     */
    Optional<User> findByEmail(final String email);
}
