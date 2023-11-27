package com.south.african.numbers.app.mapper;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class PhoneNumberMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public List<PhoneNumberEntity> mapToEntityList(List<PhoneNumber> phoneNumberList) {
        return phoneNumberList.stream()
                .map(phoneNumber -> new PhoneNumberEntity(phoneNumber))
                .collect(Collectors.toList());
    }

    public List<PhoneNumber> mapToPhoneNumbersList(List<PhoneNumberEntity> phoneNumberEntityList) {
        return phoneNumberEntityList.stream()
                .map(phoneNumberEntity -> new PhoneNumber(phoneNumberEntity))
                .collect(Collectors.toList());
    }



    public static <T> List<T> filterByStatus(List<T> items, Predicate<T> predicate) {
        return items.stream()
                .filter(predicate)
                .collect(java.util.stream.Collectors.toList());
    }

}
