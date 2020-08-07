package ru.x5.camunda.access.example.service;

public interface AccessRequestService {
    Long createAccessRequest(String username, Long entityId);
    void updateAccessRequest(String approve, String approver, Long entityId);
}
