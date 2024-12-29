package com.financial.transaction.usersystem.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEvent(String topicName, String key, Object payload) {
        try {
            kafkaTemplate.send(topicName, key, payload);
            LOG.info("Published event to topic: {} with payload: {}", topicName, payload);
        } catch (Exception e) {
            LOG.info("Failed to send message to kafka : {}", e.getLocalizedMessage());
        }
    }
}
