package ru.x5.camunda.access.example.domain;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "access_request")
public class AccessRequest {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "approver")
    private String approver;

    @Column(name = "is_granted")
    private Boolean isGranted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Boolean getGranted() {
        return isGranted;
    }

    public void setGranted(Boolean granted) {
        isGranted = granted;
    }
}
