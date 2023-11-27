package com.south.african.numbers.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorEnum {

    CSV_ERROR("CSV Error"),
    CSV_FORMAT_ERROR("The file format must be CSV"),
    FILE_NOT_NULL("The file is a required field and cannot be null"),
    ERROR_400("400 Bad Request"),
    ERROR_404("404"),
    ERROR_415("415 Unsupported Media Type"),
    ERROR_500("500 Internal Server Error");


    private String code;

}
