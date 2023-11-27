package com.south.african.numbers.app.repository;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {

    public List<PhoneNumberEntity> findAllByOrderByStatusAsc();

    public List<PhoneNumberEntity> findByStatus(String status);

    public PhoneNumberEntity findByPhoneNumber(String phoneNumber);
}
