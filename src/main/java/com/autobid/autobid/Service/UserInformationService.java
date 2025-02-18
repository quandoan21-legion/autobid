package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UserInformationService {
    @Autowired
    private UserInformationRepo userInformationRepo;

    public users registerNewUser(users users) throws NoSuchAlgorithmException {
        // Get the MessageDigest instance for SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Update the messageDigest with the password bytes
        byte[] hashedBytes = messageDigest.digest(users.getPassword().getBytes());

        // Encode the hashed bytes into a Base64 string
        users.setPassword(Base64.getEncoder().encodeToString(hashedBytes));


        return userInformationRepo.save(users);
    }
}
