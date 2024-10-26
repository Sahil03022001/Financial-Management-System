package com.financial.transaction.system.kafka.publisher;

import com.financial.transaction.system.kafka.producer.KafkaProducer;
import com.financial.transaction.system.response.UserResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserChangeEventPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(UserChangeEventPublisher.class);

    private static final String userChangeTopic = "user-change-topic";

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void publishUserChangeEventToKafka(UserResponseDto userResponseDTO) {
        try {
            kafkaTemplate.send(userChangeTopic, userResponseDTO.getUserId(), userResponseDTO);
            LOG.info("Published event to topic: {} with payload: {}", userChangeTopic, userResponseDTO);
        } catch (Exception e) {
            LOG.info("Failed to send message to kafka : {}", e.getLocalizedMessage());
        }
    }
}
