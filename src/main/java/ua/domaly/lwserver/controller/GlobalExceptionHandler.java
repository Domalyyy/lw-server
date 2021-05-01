package ua.domaly.lwserver.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.domaly.lwserver.entity.RestEntity;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Method to handle {@link RuntimeException}.
     *
     * @param ex exception
     * @return response
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestEntity> handleException(final RuntimeException ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new RestEntity("Something went wrong", StringUtils.EMPTY), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Method to handle {@link BadCredentialsException}.
     *
     * @param ex exception
     * @return response
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestEntity> handleException(final BadCredentialsException ex) {
        log.error(ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Method to handle {@link IllegalStateException}.
     *
     * @param ex exception
     * @return response
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<RestEntity> handleException(final IllegalStateException ex) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new RestEntity(ex.getMessage(), StringUtils.EMPTY), HttpStatus.NOT_FOUND);
    }
}



