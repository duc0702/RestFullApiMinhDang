package com.example.buoi01.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqLoginDTO {
    @NotNull
    @NotBlank(message = "Không được để trống")
    
    private String email;
    @NotNull
    @NotBlank(message = "Không được để trống")
    private String password;
}
