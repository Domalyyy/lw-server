package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.User;
import ua.domaly.lwserver.entity.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for {@link User}.
 */
@Service
public interface UserService {
    /**
     * Method to find a user by id.
     *
     * @param id id of user.
     * @return user within optional.
     */
    @Transactional
    Optional<User> findById(Integer id);

    /**
     * Method to find a user by email.
     *
     * @param email an email.
     * @return user within optional.
     */
    @Transactional
    Optional<User> findByEmail(String email);

    /**
     * Method to create a new user.
     *
     * @param user {@link User}.
     * @return user within optional.
     */
    @Transactional
    Optional<User> save(User user);

    /**
     * Method to update user.
     *
     * @param user {@link User}.
     */
    @Transactional
    void update(User user);

    /**
     * Method to check gradation update.
     */
    @Transactional
    User checkAndUpdateGradation(User user);

    /**
     * Method to get needed count to update.
     *
     * @return needed count to update.
     */
    @Transactional
    Integer getNeededCountToUpdate(User user);

    @Transactional
    List<UserDTO> getUsersByProgrammingLanguage(String programmingLanguage, Integer userId);

    @Transactional
    void sendFriendRequest(Integer firstUserId, Integer secondUserId);

    @Transactional
    void cancelFriendRequest(Integer firstUserId, Integer secondUserId);

    @Transactional
    List<UserDTO> getFriendRequests(Integer userId);

    @Transactional
    void submitFriendRequests(Integer firstUserId, Integer secondUserId);

    @Transactional
    void declineFriendRequests(Integer firstUserId, Integer secondUserId);

    @Transactional
    List<UserDTO> getFriends(Integer userId);

    @Transactional
    void deleteFriend(Integer firstUserId, Integer secondUserId);
}
