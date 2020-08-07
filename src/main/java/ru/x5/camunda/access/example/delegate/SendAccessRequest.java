package ru.x5.camunda.access.example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.x5.camunda.access.example.config.ProcessVariablesConstants;
import ru.x5.camunda.access.example.domain.AccessRequest;
import ru.x5.camunda.access.example.kafka.KafkaSender;
import ru.x5.camunda.access.example.kafka.Message;
import ru.x5.camunda.access.example.kafka.data.AccessRequestMessage;

@Component
public class SendAccessRequest implements JavaDelegate {

    private final KafkaSender sender;

    public SendAccessRequest(KafkaSender sender) {
        this.sender = sender;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String username = (String) delegateExecution.getVariable(ProcessVariablesConstants.USER_NAME);
        Long entityId = (Long) delegateExecution.getVariable(ProcessVariablesConstants.ENTITY_ID);
        Long requestId = (Long) delegateExecution.getVariable(ProcessVariablesConstants.REQUEST_ID);
        String applicationId = delegateExecution.getProcessBusinessKey();
        AccessRequestMessage accessRequestMessage = new AccessRequestMessage();
        accessRequestMessage.setEntityId(entityId);
        accessRequestMessage.setUsername(username);
        accessRequestMessage.setRequestId(requestId);
        accessRequestMessage.setApplicationId(applicationId);

        Message<AccessRequestMessage> message = new Message<>("AccessRequest", accessRequestMessage);
        sender.send(message);
    }
}
