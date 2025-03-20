package com.autobid.autobid.Service;


import com.autobid.autobid.DTO.ChangePasswordRequestDTO;
import com.autobid.autobid.Entity.Transaction;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.TransactionRepo;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserInformationService {
    MessageFactory message = new MessageFactory();

    @Autowired
    private UserInformationRepo userInformationRepo;

    @Autowired
    private TransactionRepo transactionRepo;

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
        users.setCreated_at(new Date());
        userInformationRepo.save(users);
        return message.MessageResponse("User created", true, List.of(userInformationRepo.save(users)));
    }

    @Transactional
    public MessageFactory withdraw(Integer userId, double amount) {
        Optional<users> userOptional = userInformationRepo.findById(userId);
        if (userOptional.isEmpty()) {
            return new MessageFactory().MessageResponse("User not found", false, List.of());
        }

        users user = userOptional.get();
        if (user.getBalance() < amount) {
            return new MessageFactory().MessageResponse("Insufficient balance", false, List.of());
        }

        user.setBalance(user.getBalance() - amount);
        userInformationRepo.save(user);

        // Create and save a transaction record
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setTransactionType("WITHDRAW");
        transaction.setAmount(amount);
        transactionRepo.save(transaction);

        return new MessageFactory().MessageResponse("Withdrawal successful", true, List.of(user));
    }

    // In UserInformationService.java
    public MessageFactory changePassword(ChangePasswordRequestDTO request) throws NoSuchAlgorithmException {
        Optional<users> userOptional = userInformationRepo.findById(request.getId());

        if (userOptional.isEmpty()) {
            return message.MessageResponse("User not found", false, List.of());
        }

        users user = userOptional.get();
        String hashedCurrentPassword = this.hashedPassword(request.getCurrent_password());

        // Check if current password matches
        if (!user.getPassword().equals(hashedCurrentPassword)) {
            return message.MessageResponse("Current password is incorrect", false, List.of());
        }

        // Hash and set new password
        String hashedNewPassword = this.hashedPassword(request.getNew_password());
        user.setPassword(hashedNewPassword);
        users updatedUser = userInformationRepo.save(user);

        // Return the updated user information
        return message.MessageResponse("Password changed successfully", true, List.of(updatedUser));
    }

    @Transactional
    public MessageFactory deposit(Integer userId, double amount) {
        Optional<users> userOptional = userInformationRepo.findById(userId);
        if (userOptional.isEmpty()) {
            return new MessageFactory().MessageResponse("User not found", false, List.of());
        }

        users user = userOptional.get();
        user.setBalance(user.getBalance() + amount);
        userInformationRepo.save(user);

        // Create and save a transaction record
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(amount);
        transactionRepo.save(transaction);

        return new MessageFactory().MessageResponse("Deposit successful", true, List.of(user));
    }


    public MessageFactory editAccountInformation(users updatedUser) throws NoSuchAlgorithmException {
        Optional<users> userOptional = userInformationRepo.findById(updatedUser.getId());
        if (userOptional.isPresent()) {
            users existingUser = userOptional.get();
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null) {
                existingUser.setPassword(this.hashedPassword(updatedUser.getPassword()));
            }
            if (updatedUser.getImage_url() != null) {
                existingUser.setImage_url(updatedUser.getImage_url());
            }
            if (updatedUser.getBio() != null) {
                existingUser.setBio(updatedUser.getBio());
            }
            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }
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

