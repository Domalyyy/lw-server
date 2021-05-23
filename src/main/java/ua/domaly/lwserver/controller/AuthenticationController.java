package ua.domaly.lwserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.dto.AuthRequest;
import ua.domaly.lwserver.entity.dto.UserRegistrationDTO;
import ua.domaly.lwserver.entity.dto.UserView;
import ua.domaly.lwserver.service.AuthenticationService;

import javax.validation.Valid;

/**
 * Rest Controller to do auth.
 */
@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * API to login.
     *
     * @return authenticated user.
     */
    @PostMapping("/login")
    public ResponseEntity<UserView> login(@RequestBody @Valid final AuthRequest authRequest) {
        final var userView = authenticationService.login(authRequest)
                .orElseThrow(() -> new BadCredentialsException("Incorrect username or password"));

        return new ResponseEntity<>(userView, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserView> signup(@RequestBody final UserRegistrationDTO userRegistrationDTO) {
        final var userView = authenticationService.register(userRegistrationDTO)
                .orElseThrow(() -> new IllegalArgumentException("Unsuccessful registration"));

        return new ResponseEntity<>(userView, HttpStatus.CREATED);
    }
}
