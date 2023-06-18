package org.tutorial.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
//@EnableSpringConfigured
public class TutorialApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TutorialApiApplication.class, args);
    }
}