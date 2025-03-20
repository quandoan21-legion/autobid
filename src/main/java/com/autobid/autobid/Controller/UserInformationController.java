package com.autobid.autobid.Controller;

import com.autobid.autobid.DTO.ChangePasswordRequestDTO;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.UserInformationRepo;
import com.autobid.autobid.Service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("/withdraw")
    public MessageFactory withdraw(@RequestBody WithdrawDepositRequest request) {
        return userInformationService.withdraw(request.getUserId(), request.getAmount());
    }

    @PostMapping("/deposit")
    public MessageFactory deposit(@RequestBody WithdrawDepositRequest request) {
        return userInformationService.deposit(request.getUserId(), request.getAmount());
    }

    @GetMapping("/account")
    public MessageFactory GetAccountInformation(@RequestParam("user_id") Integer user_id) {
        return userInformationService.getAccountInformation(user_id);
    }

    @PostMapping("/register")
    public MessageFactory RegisterNewUser(@RequestBody users users) throws NoSuchAlgorithmException {
        return userInformationService.registerNewUser(users);
    }

    @PostMapping("/login")
    public MessageFactory Login(@RequestBody users users) throws NoSuchAlgorithmException {
        return userInformationService.login(users);
    }

    @PutMapping("/update-account")
    public MessageFactory UpdateAccount(@RequestBody users users) throws NoSuchAlgorithmException {
        return userInformationService.editAccountInformation(users);
    }

    @Autowired
    private UserInformationRepo userInformationRepo;

    @GetMapping("/admin/users")
    public MessageFactory getAllUsers(@RequestParam("admin_id") Integer adminId) {
        // Check if the user is an admin
        Optional<users> adminOpt = userInformationRepo.findById(adminId);
        if (adminOpt.isEmpty() || !adminOpt.get().isAdmin()) {
            return new MessageFactory().MessageResponse("Only admins can access this endpoint", false, List.of());
        }

        // Fetch all users
        List<users> allUsers = userInformationRepo.findAll();

        return new MessageFactory().MessageResponse("All users retrieved successfully", true, allUsers);
    }

    // In UserInformationController.java
    @PostMapping("/account/change-password")
    public MessageFactory changePassword(@RequestBody ChangePasswordRequestDTO request) throws NoSuchAlgorithmException {
        return userInformationService.changePassword(request);
    }

}
