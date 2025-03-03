package com.example.buoi01.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserDto {
    private long id;
    private  String name;
    private String email;

}
