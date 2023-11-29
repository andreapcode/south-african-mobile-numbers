package com.south.african.numbers.app.controller;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.model.StatusEnum;
import com.south.african.numbers.app.repository.PhoneNumberRepository;
import com.south.african.numbers.app.service.PhoneNumberService;
import com.south.african.numbers.app.utils.TestUtils;
import com.south.african.numbers.app.utils.constant.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PhoneNumberRepository databaseStoreRepository;


    @Test
    void uploadFileAndSavePhoneNumbers() throws Exception {

        ClassPathResource uploadFile = new ClassPathResource("Interlogica_Test Pre-selezione_South_African_Mobile_Numbers.csv");

        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", "text/csv", uploadFile.getInputStream());

        when(databaseStoreRepository.saveAll(Mockito.anyList())).thenReturn(TestUtils.createPhoneNumberEntityList());

        MvcResult result = mockMvc.perform(multipart("/v1.0/south-african-number/phone-numbers")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated()).andReturn();

        String message = "{\"message\":\"1 south african phone numbers have been saved\"}";

        assertEquals(message, result.getResponse().getContentAsString());
    }

    @Test
    void uploadWrongKeyFileName() throws Exception {

        ClassPathResource uploadFile = new ClassPathResource("");

        MockMultipartFile file = new MockMultipartFile(
                "test", "test.csv", "text/csv", uploadFile.getInputStream());


        MvcResult result = mockMvc.perform(multipart("/v1.0/south-african-number/phone-numbers")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest()).andReturn();

        String message = "{\"code\":\"400 Bad Request\",\"message\":\"The file is a required field and cannot be null\"}";

        assertEquals(message, result.getResponse().getContentAsString());
    }

    @Test
    void uploadUnsupportedFile() throws Exception {

        ClassPathResource uploadFile = new ClassPathResource("dummy.pdf");

        MockMultipartFile file = new MockMultipartFile(
                "file", "test.pdf", "text/pdf", uploadFile.getInputStream());

        MvcResult result = mockMvc.perform(multipart("/v1.0/south-african-number/phone-numbers")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isUnsupportedMediaType()).andReturn();

        String response = "{\"code\":\"415 Unsupported Media Type\",\"message\":\"The file format must be CSV\"}";

        assertEquals(response, result.getResponse().getContentAsString());
    }


    @Test
    void findAllPhoneNumbers() throws Exception {

        when(databaseStoreRepository.findAllByOrderByStatusAsc()).thenReturn(TestUtils.createPhoneNumberEntityList());

        String phoneNumberMocked = "{\"phoneNumbers\":[{\"id\":\"103427070\",\"phoneNumber\":\"27714322560\",\"status\":{\"code\":\"ACCEPTED\",\"description\":\"Phone Number Accepted\"}}],\"message\":null}";


        MvcResult result = mockMvc.perform(get("/v1.0/south-african-number/phone-numbers"))
                .andExpect(status().isOk()).andReturn();


        assertEquals(phoneNumberMocked, result.getResponse().getContentAsString());
    }

    @Test
    void emptyPhoneNumberList() throws Exception {


        String responseMessage = "{\"phoneNumbers\":[],\"message\":\"The phone number list is empty\"}";


        MvcResult result = mockMvc.perform(get("/v1.0/south-african-number/phone-numbers"))
                .andExpect(status().isOk()).andReturn();


        assertEquals(responseMessage, result.getResponse().getContentAsString());
    }

    @Test
    void findPhoneNumbersByStatus() throws Exception {


        String phoneNumberMocked = "{\"phoneNumbers\":[{\"id\":\"103427070\",\"phoneNumber\":\"27714322560\",\"status\":{\"code\":\"ACCEPTED\",\"description\":\"Phone Number Accepted\"}}],\"message\":null}";

        when(databaseStoreRepository.findByStatus(StatusEnum.ACCEPTED.getCode())).thenReturn(TestUtils.createPhoneNumberEntityList());


        MvcResult result = mockMvc.perform(get("/v1.0/south-african-number/phone-numbers/status/{status}", StatusEnum.ACCEPTED.getCode()))
                .andExpect(status().isOk())
                .andReturn();


        assertEquals(phoneNumberMocked, result.getResponse().getContentAsString());
    }

    @Test
    void findPhoneNumbersByWrongStatus() throws Exception {


        String messageResponse = "{\"phoneNumbers\":[],\"message\":\"The phone number list is empty\"}";

        List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
        String wrongStatus = "TEST_STATUS";
        when(databaseStoreRepository.findByStatus(wrongStatus)).thenReturn(phoneNumberEntities);


        MvcResult result = mockMvc.perform(get("/v1.0/south-african-number/phone-numbers/status/{status}", StatusEnum.ACCEPTED.getCode()))
                .andExpect(status().isOk())
                .andReturn();


        assertEquals(messageResponse, result.getResponse().getContentAsString());
    }


    @Test
    void findPhoneNumber() throws Exception {


        String phoneNumberMocked = "{\"phoneNumber\":{\"id\":\"103427070\",\"phoneNumber\":\"27714322560\",\"status\":{\"code\":\"ACCEPTED\",\"description\":\"Phone Number Accepted\"}},\"message\":null}";
        String pNumber = "27714322560";

        when(databaseStoreRepository.findByPhoneNumber(pNumber)).thenReturn(new PhoneNumberEntity("103427070", pNumber, StatusEnum.ACCEPTED.getCode(), StatusEnum.ACCEPTED.getDescription()));


        MvcResult result = mockMvc.perform(get("/v1.0/south-african-number/phone-number/{phoneNumber}", pNumber))
                .andExpect(status().isOk())
                .andReturn();


        assertEquals(phoneNumberMocked, result.getResponse().getContentAsString());
    }



}
