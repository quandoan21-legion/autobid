package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<orders, Integer> {

    @Query("SELECT o FROM orders o WHERE o.user_id = :userId")
    List<orders> findAllByUserId(Integer userId);

    @Query("SELECT o FROM orders o WHERE o.id = :id AND o.user_id = :userId")
    List<orders> findByIdAndUserId(Integer id, Integer userId);
}