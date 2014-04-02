package com.atlauncher.exceptions;

public final class InvalidCallbackException extends RuntimeException{
    public InvalidCallbackException(){
        super("Callbacks aren't supposed to return anything but null");
    }
}