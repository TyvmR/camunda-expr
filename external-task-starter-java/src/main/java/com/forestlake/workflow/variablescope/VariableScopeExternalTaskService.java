package com.forestlake.workflow.variablescope;


import org.apache.http.client.utils.DateUtils;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
public class VariableScopeExternalTaskService {
    /**
     * 模拟上传一批一图片
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "dispatch_tasks", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler dispatchTasks() {

        return (externalTask, externalTaskService) -> {
            System.out.println("上传一批图片");
            VariableMap variables = Variables.createVariables();
            List<Integer> pictureList = new ArrayList<>();
            //模拟上传50张图片
            for (int i=1;i<=20;i++){
                pictureList.add(i);
            }
            variables.put("pictures",pictureList);
            //所有图片集合变量范围需要传播，并且不会涉及到并发安全问题，使用全局变量即可
            externalTaskService.complete(externalTask,variables);

        };
    }

    /**
     * 添加图片名称
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "add_pic_name", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler addPicName() {

        return (externalTask, externalTaskService) -> {
            System.out.println("-----------------进入添加图片名称----------------");
            Integer pictureId = externalTask.getVariable("picture");
            Map<String, Object> allVariables = externalTask.getAllVariables();
            allVariables.put("picName","pic_"+pictureId+".jpg");
            System.out.println("pictureId="+ pictureId+" ,picName"+"pic_"+pictureId+".jpg");
            externalTaskService.complete(externalTask,null,allVariables);

        };
    }

    /**
     * 去水印
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "water_print_delete", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler waterPrintDelete() {

        return (externalTask, externalTaskService) -> {
            System.out.println("------------------------进入去水印任务------------------------");
            String picName = externalTask.getVariable("picName");
            Integer picId = externalTask.getVariable("picture");
            Date date = new Date();
            String dateStr = DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss");
            System.out.println("picId="+picId+" ,picName="+picName+" ,date="+dateStr);

            VariableMap variables = Variables.createVariables();
            variables.put("picDate",dateStr);
            externalTaskService.complete(externalTask,null,variables);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "face_detect", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler faceDetect() {

        return (externalTask, externalTaskService) -> {
            System.out.println("------------------------进入人脸提取任务------------------------");
            Integer picId = externalTask.getVariable("picture");
            String picName = externalTask.getVariable("picName");
            String picFace = picId+"_face";
            System.out.println("picId="+picId+" ,picName="+picName+" ,picFace="+picFace);

            VariableMap variables = Variables.createVariables();
            variables.put("picFace",picFace);
            externalTaskService.complete(externalTask,null,variables);
        };
    }

    /**
     * 删除日期
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "pic_date_delete", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler picDateDelete() {

        return (externalTask, externalTaskService) -> {
            System.out.println("------------------------进入删除日期任务------------------------");
            String picName = externalTask.getVariable("picName");
            Integer picId = externalTask.getVariable("picture");
            String picDate = externalTask.getVariable("picDate");
            String picIdUpperComplete = picId+"_upper_delete";
            System.out.println("picId="+picId+" ,picName="+picName+" ,date="+picDate+",picIdUpperComplete="+picIdUpperComplete);

            VariableMap variables = Variables.createVariables();
            variables.put("picIdUpperComplete",picIdUpperComplete);
            externalTaskService.complete(externalTask,null,variables);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "car_detect", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler carDetect() {

        return (externalTask, externalTaskService) -> {
            System.out.println("------------------------进入车辆提取任务------------------------");
            String picName = externalTask.getVariable("picName");
            Integer picId = externalTask.getVariable("picture");
            String picFace = externalTask.getVariable("picFace");
            String picIdLowerComplete = picId+"_lower_delete";
            System.out.println("picId="+picId+" ,picName="+picName+" ,picFace="+picFace+",picIdLowerComplete="+picIdLowerComplete);

            VariableMap variables = Variables.createVariables();
            variables.put("picIdLowerComplete",picIdLowerComplete);
            externalTaskService.complete(externalTask,null,variables);
        };
    }

    /**
     * 上传单个图片
     * @return
     */
    @Bean
    @ExternalTaskSubscription(topicName = "upload_single", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler uploadSingle() {

        return (externalTask, externalTaskService) -> {
            System.out.println("------------------------进入单个上传任务------------------------");
            String picName = externalTask.getVariable("picName");
            Integer picId = externalTask.getVariable("picture");
            System.out.println("picId="+picId+" ,picName="+picName);
            externalTaskService.complete(externalTask);
        };
    }

    @Bean
    @ExternalTaskSubscription(topicName = "download_pictures", processDefinitionKeyIn = {"Process_variable_scope"},lockDuration=50000)
    public ExternalTaskHandler downloadPictures() {

        return (externalTask, externalTaskService) -> {
            System.out.println("------------------------进入下载所有图片------------------------");
            String picName = externalTask.getVariable("picName");
            Integer picId = externalTask.getVariable("picture");
            System.out.println("picId="+picId+" ,picName="+picName);
            externalTaskService.complete(externalTask);
        };
    }

}
