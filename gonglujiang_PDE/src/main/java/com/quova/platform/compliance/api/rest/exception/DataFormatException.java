package com.quova.platform.compliance.api.rest.exception;

public class DataFormatException extends RuntimeException {

    public DataFormatException(String s, Throwable t) {
        super(s, t);
    }

    public DataFormatException(Throwable t) {
        super(t.getMessage(), t);
    }

    public DataFormatException(String s) {
        super(s);
    }
}
