package ru.x5.camunda.access.example.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;
import ru.x5.camunda.access.example.domain.AccessRequest;

@Service
@EnableBinding(Sink.class)
public class KafkaListener {

    @StreamListener(target = Sink.INPUT,
            condition = "(headers['type']?:'')=='test'")
    public void getMessage(Message<AccessRequest> message) {

    }
}
