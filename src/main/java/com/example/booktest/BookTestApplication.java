package com.example.booktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookTestApplication.class, args);
    }

}
