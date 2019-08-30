package com.example.client.fallback;

import com.example.client.UserClient;
import com.example.dto.UserReq;
import com.example.dto.UserResp;
import org.springframework.stereotype.Component;

@Component
public class UserFallback implements UserClient {
    @Override
    public UserResp getUser(UserReq req) {
        UserResp resp = new UserResp();
        resp.setMessage("服务挂了");
        resp.setCode(-1);
        return resp;
    }
}
