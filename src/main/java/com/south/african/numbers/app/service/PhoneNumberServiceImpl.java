package com.south.african.numbers.app.service;

import com.south.african.numbers.app.dao.PhoneNumberDAO;
import com.south.african.numbers.app.exception.FileException;
import com.south.african.numbers.app.exception.UnsupportedFileFormatException;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.repository.PhoneNumberRepository;
import com.south.african.numbers.app.utils.FileUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    PhoneNumberDAO phoneNumberDAO;

    @Autowired
    ValidateService validateService;

    @Override
    public int save(MultipartFile file) throws FileException, UnsupportedFileFormatException {

        FileUtils.checkFileFormat(file);
        List<PhoneNumber> phoneNumberList = new ArrayList<>();


        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            for (CSVRecord record : csvParser) {
                String id = record.get(0);
                String number = record.get(1);

                PhoneNumber phoneNumber = validateService.validate(id, number);
                phoneNumberList.add(phoneNumber);


            }
        } catch (IOException e) {
            throw new FileException();
        }

        int sizeList = phoneNumberDAO.saveEntities(phoneNumberList);

        return sizeList;

    }

    @Override
    public List<PhoneNumber> findAll() {
        List<PhoneNumber> phoneNumbers = phoneNumberDAO.findAllEntities();
        return phoneNumbers;
    }

    @Override
    public List<PhoneNumber> findByStatus(String status) {
        List<PhoneNumber> phoneNumbers = phoneNumberDAO.findAllEntitiesByStatus(status);
        return phoneNumbers;
    }

    @Override
    public PhoneNumber findByPhoneNumber(String pNumber) {
        PhoneNumber phoneNumber = phoneNumberDAO.findByPhoneNumber(pNumber);
        return phoneNumber;
    }



}
