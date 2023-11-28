package com.south.african.numbers.app.service;

import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.model.StatusEnum;
import com.south.african.numbers.app.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidateServiceTest {


    @Autowired
    ValidateService validateService;


    @Test
    public void validate() {

        PhoneNumber phoneNumber = validateService.validate(TestUtils.ID, TestUtils.PHONE_NUMBER);

        assertEquals(phoneNumber.getStatus().getCode(), StatusEnum.ACCEPTED.getCode());
        assertEquals(phoneNumber.getStatus().getDescription(), StatusEnum.ACCEPTED.getDescription());
    }

    @Test
    public void validateEmptyId() {

        PhoneNumber phoneNumber = validateService.validate("", TestUtils.PHONE_NUMBER);

        assertEquals(phoneNumber.getStatus().getCode(), StatusEnum.INCORRECT_EMPTY_ID.getCode());
        assertEquals(phoneNumber.getStatus().getDescription(), StatusEnum.INCORRECT_EMPTY_ID.getDescription());
    }

    @Test
    public void validateNullIdAndPhoneNumber() {

        PhoneNumber phoneNumber = validateService.validate(null, null);

        assertEquals(phoneNumber.getStatus().getCode(), StatusEnum.INCORRECT_EMPTY_ID.getCode());
        assertEquals(phoneNumber.getStatus().getDescription(), StatusEnum.INCORRECT_EMPTY_ID.getDescription());
    }

    @Test
    public void validateRecord() {

        StatusEnum status = validateService.validateRecord(TestUtils.ID, TestUtils.PHONE_NUMBER);

        assertEquals(status, StatusEnum.ACCEPTED);
    }

    @Test
    public void validateRecordEmptyId() {

        StatusEnum status = validateService.validateRecord(null, TestUtils.PHONE_NUMBER);

        assertEquals(status, StatusEnum.INCORRECT_EMPTY_ID);
    }

    @Test
    public void validatePhoneNumber() {

        StatusEnum status = validateService.validatePhoneNumber(TestUtils.PHONE_NUMBER);

        assertEquals(status, StatusEnum.ACCEPTED);
    }

    @Test
    public void validateIncorrectPhoneNumber() {

        StatusEnum status = validateService.validatePhoneNumber(null);

        assertEquals(status, StatusEnum.INCORRECT);
    }

    @Test
    public void validateIncorrectPhoneNumberChars() {

        StatusEnum status = validateService.validatePhoneNumber(TestUtils.PHONE_NUMBER_INCORRECT_CHARS);

        assertEquals(status, StatusEnum.INCORRECT_CHARS);
    }

    @Test
    public void validateIncorrectPhoneNumberLength() {

        StatusEnum status = validateService.validatePhoneNumber(TestUtils.PHONE_NUMBER_INCORRECT_LENGTH);

        assertEquals(status, StatusEnum.INCORRECT_PHONE_NUMBER_LENGHT);
    }


    @Test
    public void validateNoPhoneNumberPrefix() {

        StatusEnum status = validateService.validatePhoneNumber(TestUtils.PHONE_NUMBER_NO_PREFIX);

        assertEquals(status, StatusEnum.INCORRECT_NO_PREFIX);
    }

    @Test
    public void validateIncorrectPhoneNumberPrefix() {

        StatusEnum status = validateService.validatePhoneNumber(TestUtils.PHONE_NUMBER_INCORRECT_PREFIX);

        assertEquals(status, StatusEnum.INCORRECT_PREFIX);
    }

    @Test
    public void validateNullId() {

        StatusEnum status = validateService.validateId(null);

        assertEquals(status, StatusEnum.INCORRECT_EMPTY_ID);
    }

    @Test
    public void validateId() {

        StatusEnum status = validateService.validateId(TestUtils.ID);

        assertEquals(status, StatusEnum.ACCEPTED);
    }

    @Test
    public void replacePrefix() {

        PhoneNumber phoneNumber = TestUtils.createPhoneNumber();
        validateService.correctPrefix(phoneNumber, StatusEnum.INCORRECT_PREFIX);

        assertEquals(phoneNumber.getStatus().getCode(), StatusEnum.CORRECTED_REPLACE_PREFIX.getCode());
        assertEquals(phoneNumber.getStatus().getDescription(), StatusEnum.CORRECTED_REPLACE_PREFIX.getDescription());
    }


    @Test
    public void correctMissingPrefix() {

        PhoneNumber phoneNumber = TestUtils.createPhoneNumber();
        validateService.correctPrefix(phoneNumber, StatusEnum.INCORRECT_NO_PREFIX);

        assertEquals(phoneNumber.getStatus().getCode(), StatusEnum.CORRECTED_MISSING_PREFIX.getCode());
        assertEquals(phoneNumber.getStatus().getDescription(), StatusEnum.CORRECTED_MISSING_PREFIX.getDescription());
    }


}
