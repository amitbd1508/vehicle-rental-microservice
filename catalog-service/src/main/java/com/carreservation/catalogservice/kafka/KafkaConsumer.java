package com.carreservation.catalogservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

//    @KafkaListener(groupId = KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.TOPIC_NAME, containerFactory = KafkaConfig.KAFKA_LISTENER_CONTAINER_FACTORY)
//    public void receivedMessage(Catalog message) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = mapper.writeValueAsString(message);
//
//        System.out.println("=====================Hello ==========================");
//        //logger.info("Json message received using Kafka listener " + jsonString);
//    }
        @org.springframework.kafka.annotation.KafkaListener(groupId =KafkaConfig.GROUP_ID_JSON, topics = KafkaConfig.TOPIC_NAME )
        public void listen(String data) {
            System.out.println("=====================Hello ==========================");
            System.out.println(data);
        }
}
