package com.adwyxx.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedLockApp
{
    public static void main(String[] args) {
        SpringApplication.run(DistributedLockApp.class, args);
    }
}
