package ru.x5.camunda.access.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.x5.camunda.access.example.domain.AccessRequest;

import java.util.Optional;

@Repository
public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    Optional<AccessRequest> findOneByUsername(String username);
    Optional<AccessRequest> findOneByEntityId(Long entityId);
}
