package org.devs.heythere_backend.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(final String msg) {
        super(msg);
    }
}
