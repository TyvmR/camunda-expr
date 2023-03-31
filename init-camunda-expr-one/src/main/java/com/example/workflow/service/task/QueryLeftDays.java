package com.example.workflow.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
@Component("queryLefDay")
public class QueryLeftDays implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String days =  String.valueOf(execution.getVariable("leftAnnualDays"));
		System.out.println("剩余天数为: " + days + "天");
	}

}
