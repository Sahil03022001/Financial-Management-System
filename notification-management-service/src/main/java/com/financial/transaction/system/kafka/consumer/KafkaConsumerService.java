package com.financial.transaction.system.kafka.consumer;

import com.financial.transaction.system.responseDTO.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "user-change-topic", groupId = "{spring.kafka.consumer.group-id}")
    public void consume(UserResponseDTO object) {
        LOG.info("Received Object: {}", object);
    }
}
