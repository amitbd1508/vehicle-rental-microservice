package com.carreservation.paymentservice.kafka;

import lombok.Value;

public class KafkaConfig {

    @Value("kafka.bootstrap.servers")
    public static String KAFKA_LOCAL_SERVER_CONFIG;

    @Value("kafka.username")
    public static String username;

    @Value("kafka.password")
    public static String password;

    public static final String GROUP_ID_STRING = "group-id-string-1";
    public static final String TOPIC_NAME = "payment";
    public static final String KAFKA_LISTENER_CONTAINER_FACTORY = "kafkaListenerContainerFactory";
    public static final String GROUP_ID_JSON = "group-id-json-1";
}