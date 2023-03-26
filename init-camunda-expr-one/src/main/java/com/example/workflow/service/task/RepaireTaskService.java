package com.example.workflow.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("doingRepair")
public class RepaireTaskService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
			System.out.println("开始上门修理");
			String currentActivityName = execution.getCurrentActivityName();
			String processDefinitionId = execution.getProcessDefinitionId();
			System.out.println("上门修理完成");
			System.out.println("流程: " + processDefinitionId + " 节点: " + currentActivityName);
			execution.setVariable("repairManName", "王小满");
			
	}
	

}
