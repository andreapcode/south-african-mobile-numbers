package com.south.african.numbers.app.entity;

import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phone_number")
public class PhoneNumberEntity {

    @Id
    @Column(unique = true, updatable = false, nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String statusDescription;

    public PhoneNumberEntity(PhoneNumber phoneNumber) {
        this.id = phoneNumber.getId();
        this.phoneNumber = phoneNumber.getPhoneNumber();
        if(phoneNumber.getStatus() != null) {
            this.status = phoneNumber.getStatus().getCode();
            this.statusDescription = phoneNumber.getStatus().getDescription();
        }
    }

}
