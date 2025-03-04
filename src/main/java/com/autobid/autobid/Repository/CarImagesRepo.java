// File: `src/main/java/com/autobid/autobid/Repository/CarImagesRepo.java`
package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.car_images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarImagesRepo extends JpaRepository<car_images, Integer> {
    List<car_images> findByCar(int car_id); // Use camelCase for the method name
}