package com.allianz.erp.exceptions;

public class IllegalOrderStatusException extends RuntimeException{
    public IllegalOrderStatusException(String message) {
        super(message);
    }
}
