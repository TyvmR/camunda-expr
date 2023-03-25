package com.forestlake.workflow.controller;


import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestClientTestController {


    private RuntimeService runtimeService;

    private RepositoryService repositoryService;

    private HistoryService historyService;

    public RestClientTestController( @Qualifier("remote")RuntimeService runtimeService,
                                     @Qualifier("remote")RepositoryService repositoryService,@Qualifier("remote")HistoryService historyService) {
        this.runtimeService = runtimeService;
        this.repositoryService = repositoryService;
        this.historyService = historyService;
    }

    @GetMapping("/test")
    public void test() {

        List<ProcessDefinition> definitions = this.repositoryService.createProcessDefinitionQuery().deploymentId("5cf74adf-32ab-11ed-9c3d-721ce79d5c92").list();
        for (ProcessDefinition definition : definitions) {
            System.out.println(definition.getKey()+": "+ definition.getVersion());
        }

        List<Deployment> deployments = this.repositoryService.createDeploymentQuery().deploymentId("5cf74adf-32ab-11ed-9c3d-721ce79d5c92").list();

        for (Deployment deployment : deployments) {
            System.out.println(deployment.getId());
        }

        List<ProcessInstance> instances = this.runtimeService.createProcessInstanceQuery().deploymentId("5cf74adf-32ab-11ed-9c3d-721ce79d5c92").list();
        for (ProcessInstance instance : instances) {
            System.out.println(instance.getId());
        }

    }
}
