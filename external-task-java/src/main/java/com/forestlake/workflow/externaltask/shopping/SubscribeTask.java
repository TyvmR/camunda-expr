package com.forestlake.workflow.externaltask.shopping;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
@Slf4j
public class SubscribeTask {
    //引擎端url 前缀
    private final static String CAMUNDA_BASE_URL = "http://localhost:8080/engine-rest";
    private ExternalTaskClient client = null;

    private ExternalTaskClient getClient() {
        if (client == null) {
            client = ExternalTaskClient.create()
                    .baseUrl(CAMUNDA_BASE_URL)
                    .asyncResponseTimeout(10000) // long polling timeout
                    .build();
        }
        return client;
    }

    @PostConstruct
    public void handleShoppingCart() {
        getClient().subscribe("shopping_cart")
                .processDefinitionKey("Process_shopping")
                .lockDuration(2000)
                .handler((externalTask, externalTaskService) -> {
                    log.info("订阅到加入购物车任务");
                    Map<String, Object> goodVariable = Variables.createVariables()
                            .putValue("size", "xl")
                            .putValue("count", 2);

                    externalTaskService.complete(externalTask, goodVariable);
                }).open();
    }


    @PostConstruct
    public void handleLogisticsDelivery() {
        getClient().subscribe("logistic_delivery")
                .processDefinitionKey("Process_shopping")
                .lockDuration(2000)
                .handler((externalTask, externalTaskService) -> {
                    Object toWhere = externalTask.getVariable("toWhere");
                    log.info("收到发货任务，目的地：{}", String.valueOf(toWhere));

                    externalTaskService.complete(externalTask);
                }).open();
    }


}
