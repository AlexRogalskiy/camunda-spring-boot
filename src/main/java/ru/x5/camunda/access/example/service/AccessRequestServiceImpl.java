package ru.x5.camunda.access.example.service;

import org.springframework.stereotype.Service;
import ru.x5.camunda.access.example.domain.AccessRequest;
import ru.x5.camunda.access.example.repository.AccessRequestRepository;

@Service
public class AccessRequestServiceImpl implements AccessRequestService {

    private final AccessRequestRepository accessRequestRepository;

    public AccessRequestServiceImpl(AccessRequestRepository accessRequestRepository) {
        this.accessRequestRepository = accessRequestRepository;
    }

    @Override
    public Long createAccessRequest(String username, Long entityId) {
        AccessRequest request = new AccessRequest();
        request.setEntityId(entityId);
        request.setUsername(username);
        return accessRequestRepository.saveAndFlush(request).getId();
    }

    @Override
    public void updateAccessRequest(String approve, String approver, Long entityId) {

        AccessRequest accessRequest = accessRequestRepository.findById(entityId).get();
        accessRequest.setGranted(approve.equals("true"));
        accessRequest.setApprover(approver);
        accessRequestRepository.saveAndFlush(accessRequest);
    }
}
