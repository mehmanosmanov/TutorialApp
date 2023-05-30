package org.tutorial.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TutorialAlreadyExistsException extends RuntimeException {
    private final HttpStatus Status = HttpStatus.CONFLICT;

    public TutorialAlreadyExistsException() {
        super("Tutorial is already exist");
    }
}
