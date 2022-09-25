package com.carreservation.catalogservice.controller;

import com.carreservation.catalogservice.entity.Catalog;
import com.carreservation.catalogservice.kafka.KafkaConfig;
import com.carreservation.catalogservice.repository.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private CatalogRepo catalogRepo;
    @GetMapping
    public String Get(){
        Catalog ct= new Catalog();
        ct.setName("Rony");
        catalogRepo.save(ct);
        return "Hello from the catalog";
    }

    @GetMapping("/all")
    public List<Catalog> GetAll(){
        return catalogRepo.findAll();
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafka")
    public String sendMessage() {

        try {
            Catalog ct= new Catalog();
            ct.setName("Kafka testing");
            kafkaTemplate.send(KafkaConfig.TOPIC_NAME, ct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "json message sent succuessfully";
    }
}
