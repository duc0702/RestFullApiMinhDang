package com.example.buoi01.controller.admin;

import com.example.buoi01.domain.User;
import com.example.buoi01.domain.request.ReqLoginDTO;
import com.example.buoi01.domain.response.ResLoginDTO;
import com.example.buoi01.domain.response.UserLoginDetail;
import com.example.buoi01.service.UserService;
import com.example.buoi01.service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final SecurityUtils securityUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@RequestBody ReqLoginDTO reqLoginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                reqLoginDTO.getEmail(), reqLoginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        Optional<User> user = userService.getUserByEmail(reqLoginDTO.getEmail(),User.class);
        User currentUser= user.get();
        String email = currentUser.getEmail();
        UserLoginDetail userLoginDetail = UserLoginDetail.builder().email(currentUser.getEmail()).name(currentUser.getName()).build();

        String accessToken =this.securityUtils.createAccessToken(email,userLoginDetail);
        ResLoginDTO resLoginDTO = ResLoginDTO.builder().accessToken(accessToken).build();
        return ResponseEntity.ok(resLoginDTO);

    }
}
