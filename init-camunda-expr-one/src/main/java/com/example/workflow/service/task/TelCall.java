package com.example.workflow.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

@Component("tellCall")
public class TelCall {
	
	public int doCall(DelegateExecution execution) {
		System.out.println("开始电话回访");
		String repairManName = String.valueOf( execution.getVariable("repairManName"));
		System.out.println("您对 " + repairManName + "的服务打几分?");
		return 10;
	}
	
	
	public void getScore(DelegateExecution execution) {
		System.out.println("查询评分");
		String repairManName = String.valueOf(execution.getVariable("repairManName"));
		int score = (int)execution.getVariable("score");
		System.out.println(repairManName + "的服务得分为: " + score);
	}
	

}
