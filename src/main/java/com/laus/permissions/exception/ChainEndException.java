package com.laus.permissions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ChainEndException extends RuntimeException {
    public ChainEndException() {
        super("Hierarchy of permissions cannot be deeper than 3 elements in a row.");
    }
}
