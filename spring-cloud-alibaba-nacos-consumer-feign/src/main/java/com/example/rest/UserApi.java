package com.example.rest;

import com.example.client.UserClient;
import com.example.dto.UserReq;
import com.example.dto.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    @Autowired
    private UserClient userClient;
    
    @RequestMapping("/getUser")
    UserResp getUser(@RequestBody UserReq req) {
        UserResp resp = userClient.getUser(req);
        return resp;
    }
}


