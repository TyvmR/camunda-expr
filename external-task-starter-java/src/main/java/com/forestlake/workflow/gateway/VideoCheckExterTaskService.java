package com.forestlake.workflow.gateway;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoCheckExterTaskService {

    @Bean
    @ExternalTaskSubscription(topicName = "checkNegtive", processDefinitionKeyIn = {"Process_call_activity_subprocess"},lockDuration=50000)
    public ExternalTaskHandler checkVideoNegtive(){
        return (externalTask, externalTaskService) -> {
            System.out.println("进入检查视频是否负面");
            Object videoName = externalTask.getVariable("targetVideoName");
            System.out.println("视频名称："+videoName+"不包含负面内容");
            externalTaskService.complete(externalTask);
        };
    }
}
