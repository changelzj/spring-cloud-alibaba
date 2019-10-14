package com.example.rest;

import com.example.dto.UserReq;
import com.example.dto.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserApi {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping("getUser")
    public UserResp getUser(@RequestBody UserReq req) {
        ServiceInstance instance = loadBalancerClient.choose("cloud-provider");
        String url = String.format("http://%s:%s/getUser", instance.getHost(), instance.getPort());
        UserResp resp = restTemplate.postForObject(url, req, UserResp.class);
        return resp;
    }
}
