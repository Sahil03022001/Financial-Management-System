package com.financial.transaction.system.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.transaction.system.entity.User;
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

    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "user-change-topic", groupId = "{spring.kafka.consumer.group-id}")
    public void consume(UserResponseDTO data) {
        LOG.info("Received Data: {}", data);
    }
}
