package ru.x5.camunda.access.example.kafka;

import java.util.Date;
import java.util.UUID;

public class Message<T> {

    // Cloud Events compliant
    private String type;
    private String id = UUID.randomUUID().toString(); // unique id of this message
    private Date time = new Date();
    private T data;
    private String dataContentType = "application/json";

    public Message() {
    }

    public Message(String type, T payload) {
        this.type = type;
        this.data = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }
}
