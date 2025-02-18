package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService {
    @Autowired
    private UserInformationRepo userInformationRepo;

    public users registerNewUser(users users){
        return userInformationRepo.save(users);
    }
}
