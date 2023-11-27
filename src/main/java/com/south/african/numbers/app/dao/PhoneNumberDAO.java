package com.south.african.numbers.app.dao;

import com.south.african.numbers.app.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PhoneNumberDAO {

    int saveEntities(List<PhoneNumber> phoneNumbers);

    List<PhoneNumber> findAllEntities();

    List<PhoneNumber> findAllEntitiesByStatus(String status);

    PhoneNumber findByPhoneNumber(String phoneNumber);
}
