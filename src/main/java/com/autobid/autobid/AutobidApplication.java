package com.autobid.autobid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutobidApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutobidApplication.class, args);
    }
}
