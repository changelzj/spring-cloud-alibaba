package com.example.rest;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.dto.UserReq;
import com.example.dto.UserResp;
import com.example.sentinel.BlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class UserApi {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RestTemplate restTemplateWithRibbon;
    
    @RequestMapping("getUser")
    @SentinelResource(value = "getUser", blockHandlerClass = BlockHandler.class, blockHandler = "deadGetUser")
    public UserResp getUser(@RequestBody UserReq req) {
        ServiceInstance instance = loadBalancerClient.choose("cloud-provider");
        String url = String.format("http://%s:%s/getUser", instance.getHost(), instance.getPort());
        UserResp resp = restTemplate.postForObject(url, req, UserResp.class);
        return resp;
    }

    
    @RequestMapping("getUser2")
    public UserResp getUser2(@RequestBody UserReq req) {
        UserResp resp = restTemplateWithRibbon.postForObject("http://cloud-provider/getUser", req, UserResp.class);
        return resp;
    }
}
