package com.example.buoi01.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDetail {
private long id;
    private String name;
    private String email;


}
