package ru.x5.camunda.access.example.kafka;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;
import ru.x5.camunda.access.example.config.ProcessVariablesConstants;
import ru.x5.camunda.access.example.domain.AccessRequest;
import ru.x5.camunda.access.example.kafka.data.AccessRequestMessage;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableBinding(Sink.class)
public class KafkaListener {

    private final RuntimeService runtimeService;


    public KafkaListener(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @StreamListener(target = Sink.INPUT,
            condition = "(headers['type']?:'')=='AccessRequest'")
    public void getAccessRequest(Message<AccessRequestMessage> message) {

        Map<String, Object> variables = new HashMap<>();
        variables.put(ProcessVariablesConstants.REQUEST_ID, message.getData().getRequestId());
        variables.put(ProcessVariablesConstants.ENTITY_ID, message.getData().getEntityId());
        variables.put(ProcessVariablesConstants.USER_NAME, message.getData().getUsername());
        variables.put(ProcessVariablesConstants.APPLICATION_ID, message.getData().getApplicationId());
        runtimeService.startProcessInstanceByMessage("AccessRequest", variables);
    }

    @StreamListener(target = Sink.INPUT,
            condition = "(headers['type']?:'')=='AccessResponse'")
    public void getAccessResponse(Message<AccessRequestMessage> message) {
        Map<String, Object> variables = new HashMap<>();
        variables.put(ProcessVariablesConstants.APPROVE, message.getData().getApprove());
        variables.put(ProcessVariablesConstants.APPROVER, message.getData().getApprover());
        variables.put(ProcessVariablesConstants.ENTITY_ID, message.getData().getEntityId());
        variables.put(ProcessVariablesConstants.REQUEST_ID, message.getData().getRequestId());

        runtimeService.correlateMessage("AccessResponse", message.getData().getApplicationId(), variables);
    }
}
