package com.example.rest;

import com.example.dto.User;
import com.example.dto.UserReq;
import com.example.dto.UserResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @Value("${server.port}")
    private String port;
    
    @RequestMapping("/getUser")
    private UserResp getUser(@RequestBody UserReq req) {
        UserResp resp = new UserResp();
        resp.setCode(200);
        resp.setMessage(port + "调用成功");
        resp.setUser(new User());
        return resp;
    }
}
