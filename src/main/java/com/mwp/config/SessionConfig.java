package com.mwp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.SpringServletContainerInitializer;

import jakarta.servlet.ServletContainerInitializer;

@Configuration
public class SessionConfig {
    public ServletContainerInitializer initializer() {
        return new SpringServletContainerInitializer();
    }
}
