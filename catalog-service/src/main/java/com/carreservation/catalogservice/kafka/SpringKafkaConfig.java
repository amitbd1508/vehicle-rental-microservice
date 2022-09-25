package com.carreservation.catalogservice.kafka;

import com.carreservation.catalogservice.entity.Catalog;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class SpringKafkaConfig {
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.KAFKA_LOCAL_SERVER_CONFIG);
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // additional props
        configMap.put("sasl.mechanism", "PLAIN");
        configMap.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='DZVQ5N4IGPVHI2TP'   password='RL/3kOXiq8eHHWAHSz4DsueV7Y3QQ3Cev3Le8aZeBajvHQARG3OmlPy8A93HF/CF';");
        configMap.put("security.protocol", "SASL_SSL");
        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.carreservation.catalogservice.entity");
        return new DefaultKafkaProducerFactory<String, Object>(configMap);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

//    @Bean
//    public ConsumerFactory<String, Catalog> consumerFactory() {
//        Map<String, Object> configMap = new HashMap<>();
//        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.KAFKA_LOCAL_SERVER_CONFIG);
//        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConfig.GROUP_ID_JSON);
//        // additional props
//        configMap.put("sasl.mechanism", "PLAIN");
//        configMap.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='DZVQ5N4IGPVHI2TP'   password='RL/3kOXiq8eHHWAHSz4DsueV7Y3QQ3Cev3Le8aZeBajvHQARG3OmlPy8A93HF/CF';");
//        configMap.put("security.protocol", "SASL_SSL");
//        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.carreservation.catalogservice.entity");
//        return new DefaultKafkaConsumerFactory<>(configMap);
//    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Catalog> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Catalog> factory = new ConcurrentKafkaListenerContainerFactory<String, Catalog>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
}
