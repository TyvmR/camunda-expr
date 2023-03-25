package com.forestlake.workflow.httpconnector;


import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckExternalService {
    @Bean
    @ExternalTaskSubscription(topicName = "male_health_plan", processDefinitionKeyIn = {"Process_http_connector"},lockDuration=50000)
    public ExternalTaskHandler maleHealthPlan() {

        return (externalTask, externalTaskService) -> {
            System.out.println("男士体检套餐");
            externalTaskService.complete(externalTask);

        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "female_health_plan", processDefinitionKeyIn = {"Process_http_connector"},lockDuration=50000)
    public ExternalTaskHandler femaleHealthPlan() {

        return (externalTask, externalTaskService) -> {
            System.out.println("女士体检套餐");
            externalTaskService.complete(externalTask);

        };
    }
}