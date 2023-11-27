package com.south.african.numbers.app.service;

import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.model.StatusEnum;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService{

    private static final String PHONE_NUMBER_PREFIX = "27";
    private static final String REGEX_NUMBERS = "[0-9]+";

    @Override
    public StatusEnum validateRecord(String id, String phoneNumber) {
        StatusEnum status = validateId(id);
        if (status.equals(StatusEnum.INCORRECT_EMPTY_ID)){
            return status;
        }
        return validatePhoneNumber(phoneNumber);
    }

    @Override
    public StatusEnum validatePhoneNumber(String phoneNumber) {

        if(StringUtils.isEmpty(phoneNumber)) {
            return StatusEnum.INCORRECT;
        }

        if(!phoneNumber.matches(REGEX_NUMBERS)) {
            return StatusEnum.INCORRECT_CHARS;
        }

        if(phoneNumber.length() != 9 && phoneNumber.length() != 11) {
            return StatusEnum.INCORRECT_PHONE_NUMBER_LENGHT;
        }

        if(!phoneNumber.startsWith(PHONE_NUMBER_PREFIX)) {
            if(phoneNumber.length() == 9) {
                return StatusEnum.INCORRECT_NO_PREFIX;
            }
            else if (phoneNumber.length() == 11) {
                return StatusEnum.INCORRECT_PREFIX;
            }

            return StatusEnum.INCORRECT;
        }

        return StatusEnum.ACCEPTED;
    }

    @Override
    public StatusEnum validateId(String id) {
        if(StringUtils.isEmpty(id)) {
            return StatusEnum.INCORRECT_EMPTY_ID;
        }
        return StatusEnum.ACCEPTED;
    }

    @Override
    public PhoneNumber validate(String id, String phoneNumber) {

        StatusEnum statusEnum = validateRecord(id, phoneNumber);

        PhoneNumber validatedPhoneNumber = new PhoneNumber(id, phoneNumber, new Status(statusEnum.getCode(), statusEnum.getDescription()));

        if (checkStatusPrefix(statusEnum)) correctPrefix(validatedPhoneNumber, statusEnum);

        return validatedPhoneNumber;
    }


    @Override
    public void correctPrefix(PhoneNumber phoneNumber, StatusEnum statusEnum) {
        if(statusEnum == StatusEnum.INCORRECT_NO_PREFIX) {
            phoneNumber.setPhoneNumber(PHONE_NUMBER_PREFIX + phoneNumber.getPhoneNumber());
            phoneNumber.getStatus().setCode(StatusEnum.CORRECTED_MISSING_PREFIX.getCode());
            phoneNumber.getStatus().setDescription(StatusEnum.CORRECTED_MISSING_PREFIX.getDescription());
        }
        else if (statusEnum == StatusEnum.INCORRECT_PREFIX) {
            phoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber().replaceAll("^.{2}", PHONE_NUMBER_PREFIX));
            phoneNumber.getStatus().setCode(StatusEnum.CORRECTED_REPLACE_PREFIX.getCode());
            phoneNumber.getStatus().setDescription(StatusEnum.CORRECTED_REPLACE_PREFIX.getDescription());
        }
    }




}
