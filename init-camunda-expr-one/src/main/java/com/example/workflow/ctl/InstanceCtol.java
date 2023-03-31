package com.example.workflow.ctl;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/userTask")
@RestController
public class InstanceCtol {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private IdentityService identityService;

	@GetMapping("/start/{processKey}")
	public void start(@PathVariable(value = "processKey") String processKey) {
		identityService.setAuthenticatedUserId("wangbing");
		 VariableMap map = Variables.createVariables();
		 map.put("isFree", false);
		 map.put("originDays", 10);
		runtimeService.startProcessInstanceByKey(processKey,map);
	}
	
	@GetMapping("/touchSignal/{siganString}")
	public void touchSignal(@PathVariable(value = "siganString") String siganString) {
		runtimeService.createSignalEvent(siganString).send();
	}
}
