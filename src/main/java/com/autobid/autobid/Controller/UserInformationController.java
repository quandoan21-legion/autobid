package com.autobid.autobid.Controller;

import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("/register")
    public users RegisterNewUser(@RequestBody users users){
        return userInformationService.registerNewUser(users);
    }
}
