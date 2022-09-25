/*
package com.carreservation.catalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Document("catalog")
@Data
@AllArgsConstructor
@Repository
public class Catalog {
    @Id
    private String id;
    private String name;


    public Catalog(){
        this.id=UUID.randomUUID().toString();
    }
}
*/
