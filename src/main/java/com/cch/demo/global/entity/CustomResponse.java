package com.cch.demo.global.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class CustomResponse<T> {
    private Integer status;
    private String message;
    private  T data;
}

