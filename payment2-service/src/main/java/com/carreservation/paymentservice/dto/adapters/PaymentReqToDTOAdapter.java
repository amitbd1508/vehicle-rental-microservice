package com.carreservation.paymentservice.dto.adapters;

import com.carreservation.paymentservice.domain.PaymentRequest;
import com.carreservation.paymentservice.dto.PaymentRequestDTO;

public class PaymentReqToDTOAdapter {


        public static PaymentRequestDTO createPaymentRequestDTO(PaymentRequest request){
            PaymentRequestDTO paymentRequestDTO=new PaymentRequestDTO();
            paymentRequestDTO.setUserId(request.getUserId());
            paymentRequestDTO.setPaymentType(request.getPaymentType());
            return paymentRequestDTO;
        }

    }

