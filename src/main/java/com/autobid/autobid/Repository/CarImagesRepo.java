package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.car_images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarImagesRepo extends JpaRepository<car_images, Integer> {

    @Modifying
    @Query("DELETE FROM car_images c WHERE c.carId = ?1")
    void deleteByCarId(Integer carId);

    List<car_images> findByCarId(int carId);
}
