package com.carreservation.notificationservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class PaymentConsumerConfig {
    //@Bean
    //@Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.KAFKA_LOCAL_SERVER_CONFIG);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfig.GROUP_ID_JSON);
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        //additional for cloud service
        configMap.put("sasl.mechanism", "PLAIN");
        configMap.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='"+KafkaConfig.USER_NAME+"'   password='"+KafkaConfig.PASSWORD+"';");
        configMap.put("security.protocol", "SASL_SSL");

        return new DefaultKafkaConsumerFactory<String, Object>(configMap);
    }

//    @Bean
//    public ConsumerFactory<String, PaymentRequestDTO> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfig());
//    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
}
