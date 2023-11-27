package com.south.african.numbers.app.controller;

import com.south.african.numbers.app.exception.*;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.rest.PhoneNumberListResponse;

import com.south.african.numbers.app.model.rest.PhoneNumberResponse;
import com.south.african.numbers.app.model.rest.SaveResponse;
import com.south.african.numbers.app.service.PhoneNumberService;
import com.south.african.numbers.app.utils.constant.Constant;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("v1.0/south-african-number")
public class PhoneNumberController {

    @Autowired
    PhoneNumberService phoneNumberService;

    @Operation(description = Constant.APIMessage.SAVE_DESCRIPTION, responses = {
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_500, description = Constant.HttpStatus.INTERNAL_SERVER_ERROR, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_500, description = Constant.HttpStatus.INTERNAL_SERVER_ERROR, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.RESPONSE_201, description = Constant.HttpStatus.CREATED, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.SAVE_RESPONSE, implementation = SaveResponse.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_400, description = Constant.HttpStatus.BAD_REQUEST, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_415, description = Constant.HttpStatus.UNSUPPORTED_MEDIA_TYPE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class)))})
    @PostMapping("/phone-numbers")
    public ResponseEntity<SaveResponse> savePhoneNumbers(MultipartFile file) throws UnsupportedFileFormatException, FileException, InvalidInputException {

        if (file == null) {
            throw new InvalidInputException(new ErrorResult(ErrorEnum.ERROR_400.getCode(), ErrorEnum.FILE_NOT_NULL.getCode()));
        }

        int sizeList = phoneNumberService.save(file);

        return new ResponseEntity<>(new SaveResponse(sizeList + Constant.APIResponseMessage.SAVE_MESSAGE), HttpStatus.CREATED);

    }


    @Operation(description = Constant.APIMessage.FIND_ALL_PHONE_NUMBERS_DESCRIPTION, responses = {
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_500, description = Constant.HttpStatus.INTERNAL_SERVER_ERROR, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.RESPONSE_200, description = Constant.HttpStatus.OK, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.PHONE_NUMBER_LIST_RESPONSE, implementation = PhoneNumberListResponse.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_400, description = Constant.HttpStatus.BAD_REQUEST, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_415, description = Constant.HttpStatus.UNSUPPORTED_MEDIA_TYPE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class)))})
    @GetMapping("/phone-numbers")
    public ResponseEntity<PhoneNumberListResponse> findAllPhoneNumbers() {

        List<PhoneNumber> phoneNumbers = phoneNumberService.findAll();

        return new ResponseEntity<>(new PhoneNumberListResponse(phoneNumbers, Constant.APIResponseMessage.PHONE_NUMBER_LIST), HttpStatus.OK);

    }

    @Operation(description = Constant.APIMessage.FIND_PHONE_NUMBERS_STATUS_DESCRIPTION, responses = {
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_500, description = Constant.HttpStatus.INTERNAL_SERVER_ERROR, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.RESPONSE_200, description = Constant.HttpStatus.OK, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.PHONE_NUMBER_LIST_RESPONSE, implementation = PhoneNumberListResponse.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_400, description = Constant.HttpStatus.BAD_REQUEST, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_415, description = Constant.HttpStatus.UNSUPPORTED_MEDIA_TYPE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class)))})
    @GetMapping(path = "/phone-numbers/status/{status}")
    public ResponseEntity<PhoneNumberListResponse> findPhoneNumbersByStatus(@PathVariable("status") String status) {

        List<PhoneNumber> phoneNumbers = phoneNumberService.findByStatus(status);

        return new ResponseEntity<>(new PhoneNumberListResponse(phoneNumbers, Constant.APIResponseMessage.PHONE_NUMBER_LIST), HttpStatus.OK);

    }

    @Operation(description = Constant.APIMessage.FIND_PHONE_NUMBER, responses = {
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_500, description = Constant.HttpStatus.INTERNAL_SERVER_ERROR, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.RESPONSE_200, description = Constant.HttpStatus.OK, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.PHONE_NUMBER_RESPONSE, implementation = PhoneNumberResponse.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_400, description = Constant.HttpStatus.BAD_REQUEST, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class))),
            @ApiResponse(responseCode = Constant.HttpStatus.ERROR_415, description = Constant.HttpStatus.UNSUPPORTED_MEDIA_TYPE, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(name = Constant.DataModel.ERROR_RESULT, implementation = ErrorResult.class)))})
    @GetMapping(path = "/phone-number/{phoneNumber}")
    public ResponseEntity<PhoneNumberResponse> findPhoneNumber(@PathVariable("phoneNumber") String pNumber) {

        PhoneNumber phoneNumber = phoneNumberService.findByPhoneNumber(pNumber);

        return ResponseEntity.status(HttpStatus.OK).body(new PhoneNumberResponse(phoneNumber, Constant.APIResponseMessage.PHONE_NUMBER));


    }
}
