package com.appchat.pub.controller;

import com.appchat.pub.Student;
import com.appchat.pub.manager.UserManager;
import com.appchat.pub.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping(value = "/login")
    public Object login(
            @RequestBody LoginRequest login){
        return userManager.login(login);
    }

    @GetMapping(value = "/test")
    public Object test(){
        Student student = new Student();
        student.setAge(10);
        student.setName("Ha");
        return student;
    }
}
