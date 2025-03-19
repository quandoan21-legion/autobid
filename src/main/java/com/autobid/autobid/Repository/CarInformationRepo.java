// File: src/main/java/com/autobid/autobid/Repository/CarInformationRepo.java
package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.car_information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CarInformationRepo extends JpaRepository<car_information, Integer> {
    @Query("SELECT c FROM car_information c WHERE c.end_time > :currentDate")
    List<car_information> findAllByEndTimeAfter(Date currentDate);

    @Query("SELECT c FROM car_information c WHERE c.end_time <= :currentDate")
    List<car_information> findAllByEndTimeBeforeOrEqual(Date currentDate);

    // sau chuyen status thanh enum va sua lai cai status nay thanh StatusEnum.INPROGRESS nhe
    @Query("SELECT c FROM car_information c WHERE c.f_user_id = :userId AND c.status = false")
    List<car_information> findInProgressCarByUserId(Integer userId);

    @Query("SELECT c FROM car_information c WHERE c.f_user_id = :userId")
    List<car_information> findByUserId(Integer userId);
}