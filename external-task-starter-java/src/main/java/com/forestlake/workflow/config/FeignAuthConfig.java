package com.forestlake.workflow.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.camunda.bpm.client.spring.boot.starter.ClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignAuthConfig {
    @Autowired
    protected ClientProperties clientProperties;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(clientProperties.getBasicAuth().getUsername(),
                clientProperties.getBasicAuth().getPassword());
    }

}