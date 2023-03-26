package com.example.workflow.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class RepairTaskPrepareService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("预约修理家电任务开始");
		
		String currentActivityName = execution.getCurrentActivityName();
		String processDefinitionId = execution.getProcessDefinitionId();
		System.out.println("预约修理家电任务完成");
		System.out.println("流程: " + processDefinitionId + " 节点: " + currentActivityName);
	}
	

}
