package ua.domaly.lwserver.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Object to present as response.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class RestEntity {
    private final String message;
    private final Object result;
}

