package com.codecool.dadsinventory;

import com.codecool.dadsinventory.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class DadsInventoryApplication {

    @Autowired
    InitService initService;

    public static void main(String[] args) {
        SpringApplication.run(DadsInventoryApplication.class, args);
    }

    @PostConstruct
    public void seedDatabase() {
        initService.seedDatabase();
    }
}
