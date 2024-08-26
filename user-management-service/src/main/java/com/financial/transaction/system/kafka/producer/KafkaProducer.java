package com.financial.transaction.system.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEvent(String topicName, String key, Object payload) {
        kafkaTemplate.send(topicName, key, payload);
        System.out.println("Published event to topic " + topicName + ": " + payload);
    }
}
