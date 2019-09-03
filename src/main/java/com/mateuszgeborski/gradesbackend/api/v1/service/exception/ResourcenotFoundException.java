package com.mateuszgeborski.gradesbackend.api.v1.service.exception;

public class ResourcenotFoundException extends RuntimeException {
    public ResourcenotFoundException() {
        super();
    }

    public ResourcenotFoundException(String message) {
        super(message);
    }

    public ResourcenotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourcenotFoundException(Throwable cause) {
        super(cause);
    }

    protected ResourcenotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
