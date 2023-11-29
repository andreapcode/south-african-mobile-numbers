package com.south.african.numbers.app.controller.support;

import com.south.african.numbers.app.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult onException(Exception e) {

        return ErrorResult.builder()
                .code(ErrorEnum.ERROR_500.getCode())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileException.class)
    public ErrorResult fileException(FileException e) {

        return ErrorResult.builder()
                .code(ErrorEnum.ERROR_500.getCode())
                .message(ErrorEnum.CSV_ERROR.getCode().concat(" - ").concat(e.getMessage()))
                .build();
    }

    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(UnsupportedFileFormatException.class)
    public ErrorResult fileFormatException(UnsupportedFileFormatException e) {

        return ErrorResult.builder()
                .code(ErrorEnum.ERROR_415.getCode())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ErrorResult clientErrorException(HttpClientErrorException e) {

        return ErrorResult.builder()
                .code(ErrorEnum.ERROR_400.getCode())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(InvalidInputException.class)
    public ErrorResult invalidInputException(InvalidInputException e) {

        return ErrorResult.builder()
                .code(e.getError().getCode())
                .message(e.getError().getMessage())
                .build();
    }


}
