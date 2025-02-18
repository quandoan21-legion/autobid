package com.autobid.autobid.Repository;

import com.autobid.autobid.Entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepo extends JpaRepository<users, Integer> {
}
