package com.south.african.numbers.app.dao;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import com.south.african.numbers.app.mapper.PhoneNumberMapper;
import com.south.african.numbers.app.model.PhoneNumber;
import com.south.african.numbers.app.model.Status;
import com.south.african.numbers.app.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhoneNumberDAOImpl implements PhoneNumberDAO{

    @Autowired
    PhoneNumberRepository databaseStoreRepository;

    @Autowired
    PhoneNumberMapper phoneNumberMapper;

    @Override
    public int saveEntities(List<PhoneNumber> phoneNumbers) {

        List<PhoneNumberEntity> phoneNumberEntities = phoneNumberMapper.mapToEntityList(phoneNumbers);

        List<PhoneNumberEntity> savedPhoneNumbers = databaseStoreRepository.saveAll(phoneNumberEntities);

        return savedPhoneNumbers.size();
    }

    @Override
    public List<PhoneNumber> findAllEntities() {

        List<PhoneNumberEntity> phoneNumberEntities = databaseStoreRepository.findAllByOrderByStatusAsc();

        List<PhoneNumber> phoneNumbers = phoneNumberMapper.mapToPhoneNumbersList(phoneNumberEntities);

        return phoneNumbers;

    }

    @Override
    public List<PhoneNumber> findAllEntitiesByStatus(String status) {

        List<PhoneNumberEntity> phoneNumberEntities = databaseStoreRepository.findByStatus(status);

        List<PhoneNumber> phoneNumbers = phoneNumberMapper.mapToPhoneNumbersList(phoneNumberEntities);

        return phoneNumbers;
    }

    @Override
    public PhoneNumber findByPhoneNumber(String pNumber) {

        PhoneNumberEntity phoneNumberEntity = databaseStoreRepository.findByPhoneNumber(pNumber);

        return new PhoneNumber(phoneNumberEntity);
    }

}
