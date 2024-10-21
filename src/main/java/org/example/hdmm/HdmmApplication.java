package org.example.hdmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HdmmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdmmApplication.class, args);
    }

}
