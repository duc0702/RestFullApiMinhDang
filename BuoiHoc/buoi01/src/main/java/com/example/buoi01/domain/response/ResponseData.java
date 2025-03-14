package com.example.buoi01.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 @Builder
public class ResponseData <T>{
    private int status;
    private String erorrs;
    private Object message;
    private T data;
}
