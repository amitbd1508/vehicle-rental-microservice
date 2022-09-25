package com.carreservation.notificationservice.entity;

import com.carreservation.notificationservice.entity.enums.NotificationStatus;

public class Notification {
    private String id;
    private String title;
    private String body;
    private String user_id;
    private NotificationStatus notificationStatus;
}
