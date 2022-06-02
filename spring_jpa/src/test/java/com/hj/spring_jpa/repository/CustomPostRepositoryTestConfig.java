package com.hj.spring_jpa.repository;

import com.hj.spring_jpa.jpa.custom.CustomPostListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPostRepositoryTestConfig {

    @Bean
    public CustomPostListener customPostListener() {
        return new CustomPostListener();
    }
}
