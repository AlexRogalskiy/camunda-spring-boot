package ru.x5.camunda.access.example.kafka.data;

public class AccessRequestMessage {

    private String username;
    private Long entityId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}
