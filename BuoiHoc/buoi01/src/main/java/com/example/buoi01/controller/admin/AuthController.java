package com.example.buoi01.controller.admin;

import com.example.buoi01.domain.User;
import com.example.buoi01.domain.request.ReqLoginDTO;
import com.example.buoi01.domain.response.ResLoginDTO;
import com.example.buoi01.domain.response.ResponseLoginDto;
import com.example.buoi01.domain.response.UserLoginDetail;
import com.example.buoi01.service.UserService;
import com.example.buoi01.service.utils.SecurityUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Value("${minh.jwt.refresh-token.validity.in.seconds}")
    private long refreshTokenExpiration;

    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@RequestBody @Valid ReqLoginDTO reqLoginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                reqLoginDTO.getEmail(), reqLoginDTO.getPassword());
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

                User currentUser = (User) this.userService.getUserByEmail(reqLoginDTO.getEmail()).orElse(null);
        
                // Khởi tạo với giá trị mặc định
                ResLoginDTO resLoginDTO = ResLoginDTO.builder().build();
                ResLoginDTO.UserLogin userLogin = ResLoginDTO.UserLogin.builder().build();
        
                if (currentUser != null) {
                    String email = currentUser.getEmail();
                    String name = currentUser.getName();
                    userLogin = ResLoginDTO.UserLogin.builder().email(email).name(name).build();
                }
        
                String accessToken = this.securityUtils.createAccessToken(reqLoginDTO.getEmail(), userLogin);
                resLoginDTO = ResLoginDTO.builder()
                        .accessToken(accessToken)
                        .user(userLogin)
                        .build();
        

           // ! Nạp thông tin hoi vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // ! Tạo refresh token
        String refresh_token = this.securityUtils.createRefreshToken(reqLoginDTO.getEmail(), resLoginDTO);

        // lưu refresh token
        this.userService.updateRefreshToken(reqLoginDTO.getEmail(), refresh_token);

        // ! Lưu refresh_token với cookie
        ResponseCookie cookie = ResponseCookie.from("refresh_token",
                refresh_token)
                .httpOnly(true)
                .path("/")
                .maxAge(refreshTokenExpiration).build();
                return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).body(resLoginDTO);
     

    }
    
       @GetMapping("/refresh")
    public ResponseEntity<ResLoginDTO> getRefreshToken(
        //Lấy giá trị của cookie refresh_token
            @CookieValue(name = "refresh_token", required = false) String refreshToken)
            throws Exception {

        if (refreshToken == null || refreshToken.isEmpty()) {

            throw new Exception("Không tìm thấy refresh token");

        }
        System.out.println("Refresh Token từ cookie: " + refreshToken); // In ra console
        // ! Kiem tra refresh token
        //Giải mã refresh token
        Jwt decodedToken = this.securityUtils.checkValidRefreshToken(refreshToken);

        //Khi giải mã không thành công
        //Lấy được email từ refresh token
        //getSubject() là lấy email từ token
        String email = decodedToken.getSubject();

        // ! Tìm refresh token trong database
       
        Optional<User> currentUser = this.userService.getUserByRefreshTokenAndEmail(email, refreshToken);
       

        if (!currentUser.isPresent()) {
            throw new Exception("Không tìm thấy refresh token");
        }


        //! Tao token mới 
        String name = currentUser.get().getName();

        ResLoginDTO.UserLogin userLogin = ResLoginDTO.UserLogin.builder().email(email).name(name).build();
        String access_token = this.securityUtils.createAccessToken(email, userLogin);
        ResLoginDTO resLoginDTO = ResLoginDTO.builder().user(userLogin).accessToken(access_token).build();
        String refresh_token = this.securityUtils.createRefreshToken(email, resLoginDTO);

        this.userService.updateRefreshToken(email, refresh_token);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", refresh_token)
                .httpOnly(true)
                .path("/")
                .maxAge(refreshTokenExpiration).build();
        return ResponseEntity.ok().header("Set-Cookie", cookie.toString()).body(resLoginDTO);
    }

    @PostMapping("/resgister")
    public ResponseEntity<String> resgister(@RequestBody @Valid User user) {
           
        return ResponseEntity.ok("Đăng ký thành công");

    }
}
