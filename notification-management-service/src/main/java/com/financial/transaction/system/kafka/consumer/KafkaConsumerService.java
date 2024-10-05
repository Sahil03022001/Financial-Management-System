package com.financial.transaction.system.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.transaction.system.entity.User;
import com.financial.transaction.system.mail.UserDetailUpdateMailService;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final String userChangeTopic = "user-change-topic";

    @Autowired
    UserDetailUpdateMailService userDetailUpdateMailService;

    @KafkaListener(topics = userChangeTopic, groupId = "{spring.kafka.consumer.group-id}")
    public void consume(UserResponseDTO data) {
        LOG.info("Received Data for topic: {}, {}", userChangeTopic, data);
        userDetailUpdateMailService.sendUserDetailUpdateMail(data);
    }
}
