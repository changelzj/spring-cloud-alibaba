package com.example.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.dto.UserReq;
import com.example.dto.UserResp;

public class BlockHandler {

    public static UserResp deadGetUser(UserReq req, BlockException e) {
        UserResp resp = new UserResp();
        resp.setMessage(e.getClass().toString());
        resp.setCode(500);
        return resp;
    }
    
}
