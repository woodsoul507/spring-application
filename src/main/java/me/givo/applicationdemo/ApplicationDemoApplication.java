package me.givo.applicationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
public class ApplicationDemoApplication implements AsyncConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationDemoApplication.class, args);
    }

}

