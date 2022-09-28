package com.carreservation.notificationservice.controller;

import com.carreservation.notificationservice.entity.Notification;
import com.carreservation.notificationservice.entity.enums.NotificationStatus;
import com.carreservation.notificationservice.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationRepo notificationRepo;
    @GetMapping("/create")
    public String CreateNotification(){
        Notification n= new Notification();
        n.setTitle("Title");
        n.setBody("Body");
        n.setUser_id("userIddd");
        n.setNotificationStatus(NotificationStatus.New);
        notificationRepo.save(n);
        return "success";
    }
    @GetMapping
    public List<Notification> getAll(){
        Notification n= new Notification();
        n.setTitle("Title");
        n.setBody("Body");
        n.setUser_id("userIddd");
        n.setNotificationStatus(NotificationStatus.New);
        return notificationRepo.findAll();

    }
}
