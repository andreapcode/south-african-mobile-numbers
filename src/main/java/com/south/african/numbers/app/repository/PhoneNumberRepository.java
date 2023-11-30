package com.south.african.numbers.app.repository;

import com.south.african.numbers.app.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {

    List<PhoneNumberEntity> findAllByOrderByStatusAsc();

    List<PhoneNumberEntity> findByStatus(String status);

    PhoneNumberEntity findByPhoneNumber(String phoneNumber);
}
