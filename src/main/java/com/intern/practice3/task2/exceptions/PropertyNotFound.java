package com.intern.practice3.task2.exceptions;

public class PropertyNotFound extends Exception {
    
    public PropertyNotFound() {
    }

    public PropertyNotFound(String message) {
        super(message);
    }

    public PropertyNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
