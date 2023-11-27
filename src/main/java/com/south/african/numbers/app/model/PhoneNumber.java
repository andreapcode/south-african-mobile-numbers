package com.south.african.numbers.app.model;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhoneNumber {

    private String id;
    private String phoneNumber;
    private Status status;


    public PhoneNumber(PhoneNumberEntity phoneNumberEntity) {
        if (phoneNumberEntity != null) {
            this.id = phoneNumberEntity.getId();
            this.phoneNumber = phoneNumberEntity.getPhoneNumber();
            this.status = new Status(phoneNumberEntity.getStatus(), phoneNumberEntity.getStatusDescription());
        }

    }
}
