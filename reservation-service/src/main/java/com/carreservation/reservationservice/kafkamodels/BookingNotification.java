package com.carreservation.reservationservice.kafkamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingNotification {
    private String userId;
    private String reservationId;
}
