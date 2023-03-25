package com.forestlake.workflow.externaltask;

import camundajar.impl.com.google.gson.JsonElement;
import camundajar.impl.com.google.gson.JsonObject;
import com.forestlake.workflow.spinjson.CustomMsg;
import com.forestlake.workflow.spinjson.ProductType;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.impl.util.JsonUtil;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class SelfRepairService {
    @Bean
    @ExternalTaskSubscription(topicName = "try_self_repair", processDefinitionKeyIn = {"Process_spin_data"}, lockDuration = 50000)
    public ExternalTaskHandler doSelfRepair() {

        return (externalTask, externalTaskService) -> {
            System.out.println("外部任务进入偿试自修");
            ProductType productType = new ProductType();
            productType.setBrand("海尔");
            productType.setPower(3);
            productType.setModel("HAIER_205");
            CustomMsg customMsg = new CustomMsg();
            customMsg.setName("xiaoming");
            customMsg.setProduct("冰箱");
            customMsg.setProductAge(2);
            customMsg.setType(productType);
            customMsg.setRepairSite(Arrays.asList("北京", "上海", "南京"));
            VariableMap variables = Variables.createVariables();
            variables.put("customMsg", JsonUtil.asString(customMsg));
            externalTaskService.complete(externalTask, variables);

        };

    }

    @Bean
    @ExternalTaskSubscription(topicName = "post_repair", processDefinitionKeyIn = {"Process_spin_data"}, lockDuration = 500000)
    public ExternalTaskHandler postRepair() {

        return (externalTask, externalTaskService) -> {
            System.out.println("邮寄自修任务");
            String customMsgStr = externalTask.getVariable("customMsg");
            JsonObject jsonObject = JsonUtil.asObject(customMsgStr);
            JsonElement product = jsonObject.get("product");
            System.out.println(product.getAsString());

            externalTaskService.complete(externalTask);

        };
    }

}
