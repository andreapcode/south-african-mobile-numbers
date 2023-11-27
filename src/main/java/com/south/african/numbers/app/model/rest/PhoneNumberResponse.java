package com.south.african.numbers.app.model.rest;

import com.south.african.numbers.app.model.PhoneNumber;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PhoneNumberResponse {

    private PhoneNumber phoneNumber;

    private String message;

    public PhoneNumberResponse(PhoneNumber phoneNumber, String message){
        if (phoneNumber == null || StringUtils.isBlank(phoneNumber.getId())){
            this.message=message;
        }
        else {
            this.phoneNumber=phoneNumber;
        }
    }

}
