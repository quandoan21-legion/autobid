package com.autobid.autobid.Controller;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
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
    public MessageFactory GetAccountInformation(@RequestParam("user_id") Integer user_id){
        return userInformationService.getAccountInformation(user_id);
    }

    @PostMapping("/register")
    public MessageFactory RegisterNewUser(@RequestBody users users) throws NoSuchAlgorithmException {
        return userInformationService.registerNewUser(users);
    }

    @PostMapping("/login")
    public  MessageFactory Login(@RequestBody users users) throws  NoSuchAlgorithmException {
        return userInformationService.login(users);
    }

    @PutMapping("/update-account")
    public MessageFactory UpdateAccount(@RequestBody users users) throws  NoSuchAlgorithmException{
        return userInformationService.editAccountInformation(users);
    }
}
