// File: src/main/java/com/autobid/autobid/Repository/BidsRepo.java
package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.bids;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidsRepo extends JpaRepository<bids, Integer> {
}