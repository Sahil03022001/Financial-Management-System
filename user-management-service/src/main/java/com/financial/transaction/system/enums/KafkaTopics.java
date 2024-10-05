package com.financial.transaction.system.enums;

public enum KafkaTopics {
    USER_CHANGE_TOPIC("user.change.topic");

    private final String topicName;

    KafkaTopics(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    @Override
    public String toString() {
        return this.topicName;
    }
    }
