package com.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResp {
    private Integer code;
    private String message;
}
