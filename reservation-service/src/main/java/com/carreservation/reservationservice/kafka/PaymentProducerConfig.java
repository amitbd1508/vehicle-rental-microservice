package com.carreservation.reservationservice.kafka;

import com.carreservation.reservationservice.kafkamodels.PaymentRequestDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class PaymentProducerConfig {

//    public ConsumerFactory<String, PaymentRequestDTO> consumerFactory() {
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.KAFKA_LOCAL_SERVER_CONFIG);
//        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfig.GROUP_ID_JSON);
//        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//
//        //additional for cloud service
//        configMap.put("sasl.mechanism", "PLAIN");
//        configMap.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='"+KafkaConfig.USER_NAME+"'   password='"+KafkaConfig.PASSWORD+"';");
//        configMap.put("security.protocol", "SASL_SSL");
//        return new DefaultKafkaConsumerFactory<>(configMap);
//    }


    public ProducerFactory<String, PaymentRequestDTO> producerFactory() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.KAFKA_LOCAL_SERVER_CONFIG);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        //additional for cloud service
        configMap.put("sasl.mechanism", "PLAIN");
        configMap.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='"+KafkaConfig.USER_NAME+"'   password='"+KafkaConfig.PASSWORD+"';");
        configMap.put("security.protocol", "SASL_SSL");
        return new DefaultKafkaProducerFactory<String, PaymentRequestDTO>(configMap);
    }

    @Bean
    public KafkaTemplate<String, PaymentRequestDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
