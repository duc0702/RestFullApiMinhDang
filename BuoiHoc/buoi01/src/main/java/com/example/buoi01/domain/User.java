package com.example.buoi01.domain;

import com.example.buoi01.domain.impl.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @NotNull
    @NotBlank(message = "Không được để trống")
    private String email;
    @NotNull
    @NotBlank(message = "Không được để trống")
    private String password;

    @Column(columnDefinition ="LONGTEXT")
        private String refreshToken;
}
