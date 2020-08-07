package ru.x5.camunda.access.example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.x5.camunda.access.example.config.ProcessVariablesConstants;
import ru.x5.camunda.access.example.kafka.KafkaSender;
import ru.x5.camunda.access.example.kafka.Message;
import ru.x5.camunda.access.example.kafka.data.AccessRequestMessage;

@Component
public class SendAccessResponse implements JavaDelegate {

    private final KafkaSender sender;

    public SendAccessResponse(KafkaSender sender) {
        this.sender = sender;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String approver = (String) delegateExecution.getVariable(ProcessVariablesConstants.APPROVER);
        String approve = (String) delegateExecution.getVariable(ProcessVariablesConstants.APPROVE);
        Long entityId = (Long) delegateExecution.getVariable(ProcessVariablesConstants.ENTITY_ID);
        Long requestId = (Long) delegateExecution.getVariable(ProcessVariablesConstants.REQUEST_ID);
        String applicationId = (String) delegateExecution.getVariable(ProcessVariablesConstants.APPLICATION_ID);
        AccessRequestMessage accessRequestMessage = new AccessRequestMessage();
        accessRequestMessage.setApprove(approve);
        accessRequestMessage.setApprover(approver);
        accessRequestMessage.setEntityId(entityId);
        accessRequestMessage.setRequestId(requestId);
        accessRequestMessage.setApplicationId(applicationId);
        Message<AccessRequestMessage> message = new Message<>("AccessResponse", accessRequestMessage);
        sender.send(message);
    }
}
