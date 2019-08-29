package com.example.client;

import com.example.dto.UserReq;
import com.example.dto.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("cloud-provider")
public interface UserClient {
    @RequestMapping("/getUser")
    UserResp getUser(@RequestBody UserReq req);
}
