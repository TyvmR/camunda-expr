package com.example.workflow.service.task;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component("dealMessageBean")
public class DealOrderBean {

	public String dealOrder(DelegateExecution execution) {
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		String typeInfo = (String)execution.getVariable("endpint");
		if(typeInfo.equals("alipay")) {
			System.out.println("启动支付宝小程序！");
			runtimeService.startProcessInstanceByMessage("Message_3r2obeb");
		}else if(typeInfo.equals("wechat")) {
			runtimeService.startProcessInstanceByMessage("Message_3aa3eng");
			System.out.println("启动微信小程序！");
		}
		return "OK";
	}
	
	public String wePay(DelegateExecution execution) {
		
		System.out.println("微信支付！");
		
		
		return "OK";
	}
	
	public String aliPay(DelegateExecution execution) {
		
		System.out.println("支付宝支付！");
		
		
		return "OK";
	}
	
	public String success(DelegateExecution execution) {
		
		System.out.println("支付完成！");
		
		return "OK";
	}
	

	
}
