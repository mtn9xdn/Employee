package com.hotelmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableJpaRepositories(basePackages = "com.hotelmanager.repository")
@EnableJpaAuditing
@SpringBootApplication
public class HotelmanagerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(HotelmanagerApplication.class, args);
    }

}
