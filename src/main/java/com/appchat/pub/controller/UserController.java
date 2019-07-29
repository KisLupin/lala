package com.appchat.pub.controller;

import com.appchat.pub.Student;
import com.appchat.pub.manager.UserManager;
import com.appchat.pub.model.database.FriendId;
import com.appchat.pub.model.request.LoginRequest;
import com.appchat.pub.repository.FriendIdRepository;
import com.appchat.pub.repository.FriendResponseRepository;
import com.appchat.pub.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private FriendResponseRepository friendResponseRepository;
    @Autowired
    private FriendIdRepository friendIdRepository;

    @Autowired
    private UserManager userManager;

    @PostMapping(value = "/login")
    public Object login(
            @RequestBody LoginRequest login){
        return userManager.login(login);
    }

    @GetMapping(value = "/getAllUser")
    public Object getAllUser(){
        return userProfileRepository.findAll();
    }

    @GetMapping(value = "/getAllFriend")
    public Object getAllFriend(
            @RequestParam int id
    ){
        return friendResponseRepository.findAllFriend(id);
    }


    @GetMapping(value = "/getAllNotFriend")
    public Object getAllNotFriend(
            @RequestParam int id
    ){
        List<FriendId> friendIds=
                friendIdRepository.findAllNotFriend(id);
        List<Integer> fIds = new ArrayList<>();
        for (FriendId friendId : friendIds) {
            if (friendId.getReceiverId() == id){
                fIds.add(friendId.getSenderId());
            }else {
                fIds.add(friendId.getReceiverId());
            }
        }
        return
                userProfileRepository.findAllNotFriend(fIds);

    }

}
