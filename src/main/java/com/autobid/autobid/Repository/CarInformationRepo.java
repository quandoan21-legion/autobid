package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.car_information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInformationRepo extends JpaRepository<car_information, Integer> {

}
