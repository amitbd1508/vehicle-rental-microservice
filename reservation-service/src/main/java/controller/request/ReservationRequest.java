package controller.request;


import entity.Account;
import entity.Duration;
import entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private Account account;
    private Duration duration;
    private PaymentType paymentType;
}
