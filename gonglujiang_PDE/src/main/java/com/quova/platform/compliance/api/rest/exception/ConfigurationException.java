package com.quova.platform.compliance.api.rest.exception;

public class ConfigurationException extends RuntimeException {

    public ConfigurationException(String s, Throwable t) {
        super(s, t);
    }

    public ConfigurationException(Throwable t) {
        super(t.getMessage(), t);
    }

    public ConfigurationException(String s) {
        super(s);
    }
}
