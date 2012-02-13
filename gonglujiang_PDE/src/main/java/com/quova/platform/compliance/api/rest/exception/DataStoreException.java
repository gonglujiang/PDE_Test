package com.quova.platform.compliance.api.rest.exception;

public class DataStoreException extends RuntimeException {

    public DataStoreException(String s, Throwable t) {
        super(s, t);
    }

    public DataStoreException(Throwable t) {
        super(t.getMessage(), t);
    }

    public DataStoreException(String s) {
        super(s);
    }
}
