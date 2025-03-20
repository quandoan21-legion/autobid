package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserId(int userId);

    List<Orders> findAllByOrderByOrderDateDesc(); // Add this method to get all orders sorted by date

}
