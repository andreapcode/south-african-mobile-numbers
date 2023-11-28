package com.south.african.numbers.app.utils;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.model.StatusEnum;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {



    public static final String ID = "103427070";
    public static final String PHONE_NUMBER = "27714322560";

    public static final String PHONE_NUMBER_INCORRECT_CHARS = "AA714322560";

    public static final String PHONE_NUMBER_INCORRECT_LENGTH = "27714322560321";

    public static final String PHONE_NUMBER_NO_PREFIX = "337143225";

    public static final String PHONE_NUMBER_INCORRECT_PREFIX = "28337143225";
    public static  List<PhoneNumberEntity> createPhoneNumberEntityList() {
        List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity("103427070", "27714322560", StatusEnum.ACCEPTED.getCode(),
                StatusEnum.ACCEPTED.getDescription());
        phoneNumberEntities.add(phoneNumberEntity);

        return phoneNumberEntities;
    }

    public static List<PhoneNumber> createPhoneNumberList() {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber("103427070", "27714322560", new Status(StatusEnum.ACCEPTED.getCode(), StatusEnum.ACCEPTED.getDescription()));
        phoneNumberList.add(phoneNumber);

        return phoneNumberList;
    }

    public static PhoneNumber createPhoneNumber(){

        return new PhoneNumber("103427070", "27714322560", new Status(StatusEnum.ACCEPTED.getCode(), StatusEnum.ACCEPTED.getDescription()));
    }
}
