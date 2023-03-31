package com.example.workflow.service.task;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("sendTask")
public class SendTask implements JavaDelegate{

	

	@Override
	public void execute(DelegateExecution execution) throws Exception {
			RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
			runtimeService.createMessageCorrelation("Message_3denngj").processInstanceBusinessKey("recieve_businessKey_1").correlate();
	}
	
	
}
