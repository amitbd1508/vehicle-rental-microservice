package com.carreservation.notificationservice.controller;

import com.carreservation.notificationservice.entity.Notification;
import com.carreservation.notificationservice.entity.enums.NotificationStatus;
import com.carreservation.notificationservice.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/{userId}")
    public List<Notification> getAll(@PathVariable String userId){

        return notificationRepo.findAll().stream().filter(n-> n.getUser_id().equals(userId)).collect(Collectors.toList());

    }

  @GetMapping("/count/{userId}")
  public long getNotificationCount(@PathVariable String userId){
    return notificationRepo.findAll().stream().filter(n-> userId.equals(n.getUser_id())).count();
  }
}
