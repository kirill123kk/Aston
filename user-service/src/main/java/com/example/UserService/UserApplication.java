package com.example.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
@SpringBootApplication
public class UserApplication {
    public static void main (String [] args){
        SpringApplication.run(UserApplication.class, args);
    }
}
