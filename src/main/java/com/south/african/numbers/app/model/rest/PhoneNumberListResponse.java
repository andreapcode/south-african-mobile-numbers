package com.south.african.numbers.app.model.rest;

import com.south.african.numbers.app.model.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberListResponse {

    private List<PhoneNumber> phoneNumbers;

    private String message;

    public PhoneNumberListResponse(List<PhoneNumber> phoneNumbers, String message){
        this.phoneNumbers=phoneNumbers;
        if (phoneNumbers.isEmpty()){
            this.message=message;
        }

    }
}
