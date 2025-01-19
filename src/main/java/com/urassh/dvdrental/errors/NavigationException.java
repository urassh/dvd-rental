package com.urassh.dvdrental.errors;

public class NavigationException extends RuntimeException {
    public NavigationException(String message, Throwable cause) {
        super(message, cause);
    }
}