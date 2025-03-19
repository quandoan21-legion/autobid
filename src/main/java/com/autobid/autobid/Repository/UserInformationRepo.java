package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInformationRepo extends JpaRepository<users, Integer> {
    // Method to find a user by username

    Optional<users> findByEmail(String email);

    Optional<users> findByUsername(String username);

    List<users> findAll();
}
