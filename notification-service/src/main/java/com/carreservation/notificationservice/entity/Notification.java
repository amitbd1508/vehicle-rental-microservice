package com.carreservation.notificationservice.entity;

import com.carreservation.notificationservice.entity.enums.NotificationStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("notification")
public class Notification {
    @Id
    private String id;
    private String title;
    private String body;
    private String user_id;
    private NotificationStatus notificationStatus;
    public Notification(){
        this.id = UUID.randomUUID().toString();
    }
}
