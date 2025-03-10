// File: `src/main/java/com/autobid/autobid/Repository/AnswerRepo.java`
package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepo extends JpaRepository<answers, Integer> {
    boolean existsByCommentId(Integer commentId);

    Optional<answers> findByCommentId(Integer commentId);
}