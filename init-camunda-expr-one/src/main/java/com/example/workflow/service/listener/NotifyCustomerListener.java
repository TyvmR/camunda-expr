package com.example.workflow.service.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("noticeCustomer")
public class NotifyCustomerListener implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("前置监听器");
	}

}
