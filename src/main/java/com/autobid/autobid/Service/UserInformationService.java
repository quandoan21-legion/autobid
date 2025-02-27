package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class UserInformationService {
    MessageFactory message = new MessageFactory();

    @Autowired
    private UserInformationRepo userInformationRepo;

    protected String hashedPassword(String passoword) throws NoSuchAlgorithmException {
        // Get the MessageDigest instance for SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Update the messageDigest with the password bytes
        byte[] hashedBytes = messageDigest.digest(passoword.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);

    }

    public void deductUserBalance(Integer userId, double amount) {
        users user = userInformationRepo.findById(userId).orElse(null);
        if (user != null) {
            user.deductBalance(amount);
            userInformationRepo.save(user);
        }
    }

    public MessageFactory getAccountInformation(Integer user_id) {
        Optional<users> userOptional = userInformationRepo.findById(user_id);
        if (userOptional.isPresent()) {
            return message.MessageResponse("This is your account info", true, List.of(userOptional.get()));
        } else {
            return message.MessageResponse("User not found", false, List.of());
        }
    }

    public MessageFactory registerNewUser(users users) throws NoSuchAlgorithmException {
        if (userInformationRepo.findByEmail(users.getEmail()).isPresent() && userInformationRepo.findByUsername(users.getUsername()).isPresent()) {
            return message.MessageResponse("Username or Email already used", false, List.of());
        }
        // Encode the hashed bytes into a Base64 string
        users.setPassword(this.hashedPassword(users.getPassword()));

        userInformationRepo.save(users);
        return message.MessageResponse("User created", true, List.of(userInformationRepo.save(users)));
    }

    public MessageFactory editAccountInformation(users updatedUser) throws NoSuchAlgorithmException {
        Optional<users> userOptional = userInformationRepo.findById(updatedUser.getId());
        if (userOptional.isPresent()) {
            users existingUser = userOptional.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(this.hashedPassword(updatedUser.getPassword()));
            existingUser.setImage_url(updatedUser.getImage_url());
            userInformationRepo.save(existingUser);
            return message.MessageResponse("Account information updated", true, List.of(existingUser));
        } else {
            return message.MessageResponse("User not found", false, List.of());
        }
    }

    public MessageFactory login(users users) throws NoSuchAlgorithmException {
        String username = users.getUsername();
        String hashed_password = this.hashedPassword(users.getPassword());

        Optional<users> userOptional = userInformationRepo.findByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(hashed_password)) {
            return message.MessageResponse("Logged In", true, List.of(userOptional.get()));
        } else {
            return message.MessageResponse("Invalid username or password", false, List.of());
        }
    }
}

