package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class DemoRestProblemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRestProblemApplication.class, args);
    }

    @Bean(name = { "defaultNemesisRepositoryRestConfigurer" })
    public RepositoryRestConfigurer defaultDemoConfigurerAdapter() {
        return new DemoConfigurerAdapter();
    }
}
