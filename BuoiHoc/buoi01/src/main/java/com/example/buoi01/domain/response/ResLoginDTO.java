package com.example.buoi01.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResLoginDTO {

    @JsonProperty("access_token")
    private String accessToken;
    private UserLoginDetail user;


}
