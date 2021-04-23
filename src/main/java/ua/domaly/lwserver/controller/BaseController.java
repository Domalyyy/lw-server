package ua.domaly.lwserver.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.domaly.lwserver.entity.RestEntity;

/**
 * Base controller.
 */
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BaseController {
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
}
