package com.example.buoi01.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration

public class CorsConfig {
    @Value("${URL_FONTEND}")
    String URL_FONRTEND ;
     @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // mở cho các cổng kết nối
        configuration.setAllowedOrigins(Arrays.asList(
            // fontend được phép kết nối
            URL_FONRTEND

        ));
        // mở cho các phương thức
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(
            //Kiểu dữ liệu được phép truyền vào header 
                Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "accept", "x-no-retry",
                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;

    }
}
