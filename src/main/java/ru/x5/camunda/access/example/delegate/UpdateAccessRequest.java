package ru.x5.camunda.access.example.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.x5.camunda.access.example.config.ProcessVariablesConstants;
import ru.x5.camunda.access.example.service.AccessRequestService;

@Component
public class UpdateAccessRequest implements JavaDelegate {
    private final AccessRequestService accessRequestService;

    public UpdateAccessRequest(AccessRequestService accessRequestService) {
        this.accessRequestService = accessRequestService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Long requestId = (Long) delegateExecution.getVariable(ProcessVariablesConstants.REQUEST_ID);
        String approve = (String) delegateExecution.getVariable(ProcessVariablesConstants.APPROVE);

        accessRequestService.updateAccessRequest(approve, "", requestId);
    }
}
