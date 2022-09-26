package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private String id;
    // private String accountId;
    private ReservationStatus reservationStatus;

    Account account;
    //    @Embedded
    private Duration duration;
    private PaymentType paymentType;

    //    @OneToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "vehicleID")
    private Vehicle vehicle;
}
