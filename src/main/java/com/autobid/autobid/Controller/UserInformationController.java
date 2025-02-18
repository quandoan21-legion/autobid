package com.autobid.autobid.Controller;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("/register")
    public MessageFactory RegisterNewUser(@RequestBody users users) throws NoSuchAlgorithmException {
        return userInformationService.registerNewUser(users);
    }

    @PostMapping("/login")
    public  MessageFactory Login(@RequestBody users users) throws  NoSuchAlgorithmException {
        return userInformationService.login(users);
    }
}
