package com.example.filter;

import com.example.dto.BaseResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class Filter implements GlobalFilter, Ordered {
    /**
     * web flux 返回mono
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String token = request.getQueryParams().getFirst("token");// 第一个名字叫token的参数
        if (token != null) {
            // 未授权，然后关闭请求
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            HttpHeaders headers = response.getHeaders();
            try {
                BaseResp resp = new BaseResp();
                resp.setMessage("请求没有授权");
                resp.setCode(401);
                ObjectMapper objectMapper = new ObjectMapper();
                byte[] bytes = objectMapper.writeValueAsBytes(resp);
                DataBuffer wrap = dataBufferFactory.wrap(bytes);
                headers.add("Content-Type", "application/json;charset=utf-8");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.writeWith(Mono.just(wrap));
            } catch (Exception e) {
                log.error("Exception.", e);
            }
            
        }
        
        // 处理完成，传递给下一个过滤器
        return chain.filter(exchange);
    }

    /**
     * 执行顺序，数字越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
