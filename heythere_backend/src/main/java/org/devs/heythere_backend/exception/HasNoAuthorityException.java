package org.devs.heythere_backend.exception;

public class HasNoAuthorityException extends RuntimeException{
    public HasNoAuthorityException(final String msg) {
        super(msg);
    }
}
