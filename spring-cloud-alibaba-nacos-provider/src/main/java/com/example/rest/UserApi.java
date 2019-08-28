package com.example.rest;

import com.example.dto.User;
import com.example.dto.UserReq;
import com.example.dto.UserResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    
    @GetMapping("/getuser")
    private UserResp getUser( UserReq req) {
        UserResp resp = new UserResp();
        resp.setUser(new User());
        return resp;
    }
}
