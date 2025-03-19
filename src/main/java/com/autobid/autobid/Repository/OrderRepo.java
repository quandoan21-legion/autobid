package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<orders, Integer> {
}
