package ua.domaly.lwserver.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.domaly.lwserver.entity.RestEntity;

/**
 * Rest Controller to do auth.
 */
@Log4j2
@RestController
public class AuthenticationController extends BaseController {

    /**
     * API to login.
     *
     * @return authenticated user.
     */
    @GetMapping("/login")
    public ResponseEntity<RestEntity> authentication() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return new ResponseEntity<>(
                new RestEntity(authentication.getName(), authentication.getAuthorities().toString()),
                HttpStatus.OK
        );
    }
}
