package com.alura.screenmatch.exception;

public class ErrorinDurationConversionException extends RuntimeException {
    private String message;
    public ErrorinDurationConversionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
