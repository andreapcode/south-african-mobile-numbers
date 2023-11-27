package com.south.african.numbers.app.exception;

public class InvalidInputException extends ApplicationException{

    public InvalidInputException(ErrorResult errorResult) {
        super(errorResult);
    }
}
