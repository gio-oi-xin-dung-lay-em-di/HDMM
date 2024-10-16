package org.example.hdmm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Cho phép tất cả các đường dẫn
                .allowedOrigins("http://localhost:3000") // Chỉ cho phép origin từ frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Các phương thức HTTP được phép
                .allowedHeaders("*") // Cho phép tất cả các headers
                .allowCredentials(true); // Cho phép gửi cookie hoặc thông tin xác thực
    }
}
