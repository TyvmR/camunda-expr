package com.forestlake.workflow;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {

  @Autowired
  private IdentityService identityService;
  @Autowired
  private RuntimeService runtimeService;


  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @GetMapping("/start/{processKey}/{businessKey}")
  public void start(@PathVariable(value = "processKey") String processKey,@PathVariable(value = "businessKey")
                    String businessKey){
    identityService.setAuthenticatedUserId("xiaoming");
//    VariableMap variables = Variables.createVariables();
//    variables.put("isFree",false);

    VariableMap variables = Variables.createVariables();
//    identityService.setAuthenticatedUserId("xiaoming");
//    List<String> leaders = new LinkedList<>();
//    leaders.add("wangbing");
//    leaders.add("zhangsan");
//    leaders.add("wangwu");
//    variables.put("leaders", leaders);
//    variables.put("originDays",10);

     List<String> videoNames = new ArrayList<>();
     videoNames.add("电影");
     videoNames.add("电视剧");
     videoNames.add("综艺节目");
    variables.put("videoNames",videoNames);
    runtimeService.startProcessInstanceByKey(processKey,businessKey,variables);

  }

    @GetMapping("/executions/{processInstanceId}")
    public List<Execution> getAllExecutions(@PathVariable(value = "processInstanceId") String processInstanceId) {
        List<Execution> list = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
        return list;
    }


    @GetMapping("/test")
    public long processEngineApi(){
        System.out.println("这是一个测试程序");
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        long count = repositoryService.createDeploymentQuery().list().stream().count();
        return count;


    }

}