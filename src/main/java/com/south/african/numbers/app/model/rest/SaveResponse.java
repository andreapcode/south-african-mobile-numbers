package com.south.african.numbers.app.model.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SaveResponse {

    private String message;

    public SaveResponse(String message) {
        this.message=message;
    }
}
