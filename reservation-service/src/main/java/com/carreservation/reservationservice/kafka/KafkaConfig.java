package com.carreservation.reservationservice.kafka;

public class KafkaConfig {
    // required username='DZVQ5N4IGPVHI2TP'   password='RL/3kOXiq8eHHWAHSz4DsueV7Y3QQ3Cev3Le8aZeBajvHQARG3OmlPy8A93HF/CF'
    public static final String KAFKA_LOCAL_SERVER_CONFIG = "pkc-3w22w.us-central1.gcp.confluent.cloud:9092";
    public static final String GROUP_ID_STRING = "group-id-string-1";
    public static final String TOPIC_NAME_RESERVATION_CREATED = "reservation-created-payment";
    public static final String UPDATE_RES_STATUS = "update-reservation-status";
    public static final String TOPIC_NAME_RESERVATION_CHANGED = "reservation-changed-catalog";
    public static final String GROUP_ID_JSON = "group-id-json-1";
    public static final String USER_NAME="DZVQ5N4IGPVHI2TP";
    public static final String TOPIC_RESERVATION_BOOKING="Reservation-Booking-Notification";
    public static final String PASSWORD="RL/3kOXiq8eHHWAHSz4DsueV7Y3QQ3Cev3Le8aZeBajvHQARG3OmlPy8A93HF/CF";
}
