package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
public class UserInformationService {
    MessageFactory message = new MessageFactory();

    @Autowired
    private UserInformationRepo userInformationRepo;

    public MessageFactory registerNewUser(users users) throws NoSuchAlgorithmException {
        if (userInformationRepo.findByEmail(users.getEmail()).isPresent() && userInformationRepo.findByUsername(users.getUsername()).isPresent()) {
            return message.MessageResponse("Username or Email already used", false, List.of());
        }
        // Get the MessageDigest instance for SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Update the messageDigest with the password bytes
        byte[] hashedBytes = messageDigest.digest(users.getPassword().getBytes());

        // Encode the hashed bytes into a Base64 string
        users.setPassword(Base64.getEncoder().encodeToString(hashedBytes));

        userInformationRepo.save(users);
        return message.MessageResponse("User created", true, List.of(userInformationRepo.save(users)));
    }

}
