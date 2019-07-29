package com.appchat.pub.manager;

import com.appchat.pub.model.database.UserProfile;
import com.appchat.pub.model.request.LoginRequest;
import com.appchat.pub.repository.UserProfileRepository;
import com.appchat.pub.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

    @Autowired
    private UserProfileRepository userProfileRepository;
    public Object login(
             LoginRequest loginRequest){
        UserProfile userProfile = userProfileRepository.findOneUserLogin(loginRequest.getUsername());
        if (userProfile == null || !userProfile.getPassword().equals(loginRequest.getPassword())){
            return BaseResponse.createResponse(0,"username or password is invalid");
        }
        return BaseResponse.createResponse(userProfile);
    }
}
