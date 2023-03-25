package com.forestlake.workflow;

import org.camunda.community.rest.EnableCamundaRestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCamundaRestClient
public class ExternalTaskStarterJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalTaskStarterJavaApplication.class, args);
    }

}
