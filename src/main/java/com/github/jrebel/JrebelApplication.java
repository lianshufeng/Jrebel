package com.github.jrebel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.github.jrebel.core")
public class JrebelApplication {

    public static void main(String[] args) {
        SpringApplication.run(JrebelApplication.class, args);
    }

}
