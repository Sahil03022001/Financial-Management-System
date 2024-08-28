package com.financial.transaction.system.kafka.publisher;

import com.financial.transaction.system.kafka.producer.KafkaProducer;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChangeEventPublisher {

    private static final String userChangeTopic = "user-change-topic";

    @Autowired
    KafkaProducer kafkaProducer;

    public void publishUserChangeEventToKafka(UserResponseDTO userResponseDTO) {
        kafkaProducer.publishEvent(userChangeTopic, userResponseDTO.getUserId(), userResponseDTO);
    }
}
