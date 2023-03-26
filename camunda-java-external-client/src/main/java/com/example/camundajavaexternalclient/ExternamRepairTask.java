package com.example.camundajavaexternalclient;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternamRepairTask implements InitializingBean {
	
	@Bean
	@ExternalTaskSubscription(topicName= "try_self_repair",processDefinitionKeyIn = {"Process_1cwatxc"},lockDuration = 5000000)
	public ExternalTaskHandler doSelfRepair() {
		
		return (task,taskService) -> {
			System.out.println("外部任务尝试自修");
			boolean isFree = (boolean)task.getVariable("isFree");
			if(isFree) {
				System.out.println("免费维修");
				taskService.handleFailure(task,"维修是免费的，我不想自修了","异常日志",0,5000);
			}else {
				System.out.println("收费维修");
				taskService.complete(task);
			}
		};
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("初始化成功!!!");
		
	}

}
