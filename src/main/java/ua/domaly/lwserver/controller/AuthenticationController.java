package ua.domaly.lwserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.MyUserDetails;
import ua.domaly.lwserver.entity.dto.AuthRequest;
import ua.domaly.lwserver.entity.dto.UserView;
import ua.domaly.lwserver.service.UserService;
import ua.domaly.lwserver.utils.JwtUtils;

import javax.validation.Valid;

/**
 * Rest Controller to do auth.
 */
@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;

    /**
     * API to login.
     *
     * @return authenticated user.
     */
    @PostMapping("/login")
    public ResponseEntity<UserView> authentication(@RequestBody @Valid AuthRequest request) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        final var authenticate = authenticationManager.authenticate(authenticationToken);
        final var myUserDetails = (MyUserDetails) authenticate.getPrincipal();
        final var user = userService.findByEmail(myUserDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("User was not found by provided email"));
        final var userView = user.toUserView();
        userView.setToken(jwtTokenUtil.generateAccessToken(user));

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                .body(userView);
    }
}
