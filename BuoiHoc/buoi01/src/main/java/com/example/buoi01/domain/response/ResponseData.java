package com.example.buoi01.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData <T>{
    private int status;
    private String erors;
    private Object message;
    private T data;
}
