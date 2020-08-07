package ru.x5.camunda.access.example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.x5.camunda.access.example.config.ProcessVariablesConstants;
import ru.x5.camunda.access.example.service.AccessRequestService;

@Component
public class CreateAccessRequest implements JavaDelegate {

    private final AccessRequestService accessRequestService;

    public CreateAccessRequest(AccessRequestService accessRequestService) {
        this.accessRequestService = accessRequestService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("New request for access...");

        String username = (String) delegateExecution.getVariable(ProcessVariablesConstants.USER_NAME);
        Long entityId = (Long) delegateExecution.getVariable(ProcessVariablesConstants.ENTITY_ID);

        Long requestId = accessRequestService.createAccessRequest(username, entityId);
        delegateExecution.setVariable(ProcessVariablesConstants.REQUEST_ID, requestId);
    }
}
