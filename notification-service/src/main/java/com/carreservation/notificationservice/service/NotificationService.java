package com.carreservation.notificationservice.service;

import com.carreservation.notificationservice.entity.Notification;
import com.carreservation.notificationservice.entity.enums.NotificationStatus;
import com.carreservation.notificationservice.model.BookingNotification;
import com.carreservation.notificationservice.model.PaymentNotification;
import com.carreservation.notificationservice.repo.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class NotificationService {
    @Autowired
    NotificationRepo notificationRepo;
    public void sendPaymentNotifications(PaymentNotification notification){
        Notification n= new Notification();
        n.setTitle("Payment Notification");
        n.setBody("Payment for your Reservation Id: "+notification.getReservationId()+" has been"+ notification.getPaymentStatus());
        n.setUser_id(notification.getUserId());

        n.setNotificationStatus(NotificationStatus.New);

        System.out.println("Email has sent");
        notificationRepo.save(n);
    }
    public void sendBookingNotification(BookingNotification bookingNotification){
        Notification n= new Notification();
        n.setTitle("Reservation Notification");
        n.setBody("You have new reservation, Reservation Id: "+bookingNotification.getReservationId());
        n.setUser_id(bookingNotification.getUserId());

        n.setNotificationStatus(NotificationStatus.New);

        //System.out.println("Email has sent");
        notificationRepo.save(n);
    }
}
