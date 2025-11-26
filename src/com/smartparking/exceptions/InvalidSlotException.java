package com.smartparking.exceptions;

public class InvalidSlotException extends RuntimeException {
    public InvalidSlotException(String message) { super(message); }
}