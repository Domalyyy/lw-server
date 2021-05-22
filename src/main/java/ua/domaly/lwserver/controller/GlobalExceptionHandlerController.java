package ua.domaly.lwserver.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller that handles exceptions.
 */
@Log4j2
@ControllerAdvice
public class GlobalExceptionHandlerController {
    /**
     * Method to handle {@link RuntimeException}.
     *
     * @param ex exception.
     * @return response.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(final RuntimeException ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to handle {@link BadCredentialsException}.
     *
     * @param ex exception.
     * @return response.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleException(final BadCredentialsException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Method to handle {@link IllegalStateException}.
     *
     * @param ex exception.
     * @return response.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleException(final IllegalStateException ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Method to handle {@link IllegalArgumentException}.
     *
     * @param ex exception.
     * @return response.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(final IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Method to handle {@link MethodArgumentNotValidException}.
     *
     * @param ex exception.
     * @return response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleException(final MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        final var errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

