package ua.domaly.lwserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.domaly.lwserver.entity.MyUserDetails;
import ua.domaly.lwserver.entity.User;
import ua.domaly.lwserver.entity.dto.AuthRequest;
import ua.domaly.lwserver.entity.dto.UserRegistrationDTO;
import ua.domaly.lwserver.entity.dto.UserView;
import ua.domaly.lwserver.service.AuthenticationService;
import ua.domaly.lwserver.service.UserService;
import ua.domaly.lwserver.utils.JwtUtils;

import java.util.Optional;

/**
 * {@inheritDoc}
 */
@Transactional
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserView> login(final AuthRequest authRequest) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        final var authenticate = authenticationManager.authenticate(authenticationToken);
        final var myUserDetails = (MyUserDetails) authenticate.getPrincipal();
        final var user = userService.findByEmail(myUserDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("Incorrect credentials"));

        final var userView = this.userToUserView(user);

        return Optional.of(userView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserView> register(final UserRegistrationDTO userRegistrationDTO) {
        final var user = this.userRegistrationDTOToUser(userRegistrationDTO);

        return userService.save(user)
                .map(createdUser -> Optional.of(this.userToUserView(user)))
                .orElseThrow(() -> new IllegalArgumentException("Unsuccessful registration"));
    }

    private UserView userToUserView(final User user) {
        return UserView.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .gradation(user.getGradation())
                .role(user.getRole())
                .token(jwtTokenUtil.generateAccessToken(user))
                .completedTasks(user.getCompletedTasks().size())
                .build();
    }

    private User userRegistrationDTOToUser(final UserRegistrationDTO userRegistrationDTO) {
        return User.builder()
                .firstName(userRegistrationDTO.getFirstName())
                .lastName(userRegistrationDTO.getLastName())
                .email(userRegistrationDTO.getEmail())
                .password(passwordEncoder.encode(userRegistrationDTO.getPassword()))
                .role(User.Role.valueOf(userRegistrationDTO.getRole()))
                .active(true)
                .build();
    }
}
