package com.vikmir.nasa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NasaPictureWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaPictureWebServiceApplication.class, args);
    }

}