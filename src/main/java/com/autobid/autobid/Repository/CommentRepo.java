// File: `src/main/java/com/autobid/autobid/Repository/CommentRepo.java`
package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<comments, Integer> {
    List<comments> findByCarId(Integer carId);
}