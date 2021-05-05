package ua.domaly.lwserver.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.dto.AuthRequest;
import ua.domaly.lwserver.entity.dto.UserRegistrationDTO;
import ua.domaly.lwserver.entity.dto.UserView;

import java.util.Optional;

/**
 * Service for authentication actions.
 */
@Transactional
@Service
public interface AuthenticationService {
    /**
     * Method to do login action.
     *
     * @param authRequest {@link AuthRequest}.
     * @return user as {@link UserView}.
     */
    Optional<UserView> login(AuthRequest authRequest);

    /**
     * Method to do register action.
     *
     * @param userRegistrationDTO {@link UserRegistrationDTO}.
     * @return user as {@link UserView}.
     */
    Optional<UserView> register(UserRegistrationDTO userRegistrationDTO);
}
