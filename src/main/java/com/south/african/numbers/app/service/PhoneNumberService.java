package com.south.african.numbers.app.service;

import com.south.african.numbers.app.exception.FileException;
import com.south.african.numbers.app.exception.UnsupportedFileFormatException;
import com.south.african.numbers.app.model.PhoneNumber;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;


public interface PhoneNumberService {
    //void downloadAsCSV(PrintWriter writer) throws CSVWriteServiceException;

    int save(MultipartFile file) throws FileException, UnsupportedFileFormatException;

    List<PhoneNumber> findAll();

    List<PhoneNumber> findByStatus(String status);

    PhoneNumber findByPhoneNumber(String phoneNumber);
}
