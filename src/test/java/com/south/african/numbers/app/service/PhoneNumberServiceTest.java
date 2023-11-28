package com.south.african.numbers.app.service;

import com.south.african.numbers.app.dao.PhoneNumberDAO;
import com.south.african.numbers.app.entity.PhoneNumberEntity;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.StatusEnum;
import com.south.african.numbers.app.repository.PhoneNumberRepository;
import com.south.african.numbers.app.service.PhoneNumberService;
import com.south.african.numbers.app.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PhoneNumberServiceTest {


    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @Mock
    private PhoneNumberDAO phoneNumberDAO;

    @InjectMocks
    private PhoneNumberServiceImpl phoneNumberService;

    @Test
    public void findAll() {

        when(phoneNumberDAO.findAllEntities()).thenReturn(TestUtils.createPhoneNumberList());
        when(phoneNumberRepository.findAllByOrderByStatusAsc()).thenReturn(TestUtils.createPhoneNumberEntityList());

        List<PhoneNumber> result = phoneNumberService.findAll();


        assertEquals(TestUtils.createPhoneNumberList().size(), result.size());
        assertEquals(TestUtils.createPhoneNumberList().get(0).getId(), result.get(0).getId());

    }

    @Test
    public void findByStatus() {

        when(phoneNumberDAO.findAllEntitiesByStatus(StatusEnum.ACCEPTED.getCode())).thenReturn(TestUtils.createPhoneNumberList());
        when(phoneNumberRepository.findByStatus(StatusEnum.ACCEPTED.getCode())).thenReturn(TestUtils.createPhoneNumberEntityList());

        List<PhoneNumber> result = phoneNumberService.findByStatus(StatusEnum.ACCEPTED.getCode());


        assertEquals(TestUtils.createPhoneNumberList().size(), result.size());
        assertEquals(TestUtils.createPhoneNumberList().get(0).getId(), result.get(0).getId());
        assertEquals(TestUtils.createPhoneNumberList().get(0).getStatus().getCode(), result.get(0).getStatus().getCode());

    }

    @Test
    public void findByPhoneNumber() {

        PhoneNumber phoneNumberMocked = TestUtils.createPhoneNumber();
        when(phoneNumberDAO.findByPhoneNumber(TestUtils.PHONE_NUMBER)).thenReturn(phoneNumberMocked);
        when(phoneNumberRepository.findByStatus(TestUtils.PHONE_NUMBER)).thenReturn(TestUtils.createPhoneNumberEntityList());

        PhoneNumber result = phoneNumberService.findByPhoneNumber(TestUtils.PHONE_NUMBER);

        assertEquals(phoneNumberMocked.getId(), result.getId());
        assertEquals(phoneNumberMocked.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(phoneNumberMocked.getStatus(), result.getStatus());

    }


}
