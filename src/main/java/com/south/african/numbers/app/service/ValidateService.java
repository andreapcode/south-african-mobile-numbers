package com.south.african.numbers.app.service;

import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.model.StatusEnum;
import io.micrometer.common.util.StringUtils;

public interface ValidateService {

    default boolean checkStatusPrefix(StatusEnum status){
        if (!status.getCode().equals(StatusEnum.ACCEPTED.getCode()) && (status.getCode().equals(StatusEnum.INCORRECT_NO_PREFIX.getCode()) || status.getCode().equals(StatusEnum.INCORRECT_PREFIX.getCode())))
            return true;

        return false;
    }
    StatusEnum validateRecord(String id, String phoneNumber);

    StatusEnum validatePhoneNumber(String phoneNumber);

    StatusEnum validateId(String id);

    PhoneNumber validate(String id, String phoneNumber);

    void correctPrefix(PhoneNumber phoneNumber, StatusEnum statusEnum);


}
