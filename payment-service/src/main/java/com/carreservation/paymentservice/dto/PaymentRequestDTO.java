package com.carreservation.paymentservice.dto;


    public class PaymentRequestDTO {
        private String paymentType;
        private String userId;

        public PaymentRequestDTO(String paymentType,String userId) {
            this.paymentType = paymentType;
            this.userId = userId;
        }

        public PaymentRequestDTO() {

        }

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }


        @Override
        public String toString() {
            return "PaymentRequestDTO{" +
                    "paymentType='" + paymentType + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }
    }

