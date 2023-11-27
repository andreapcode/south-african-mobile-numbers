package com.south.african.numbers.app.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResult {

    private String code;
    private String message;
}
