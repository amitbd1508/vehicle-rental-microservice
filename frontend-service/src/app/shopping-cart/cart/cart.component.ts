import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { CartItem } from '../../shared/models/cart';
import { ProductService } from '../../product/product.service';
import { Router } from '@angular/router';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { LoggerService } from '../../shared/service/logger.service';
import {ReservationItem} from "../../shared/models/reservation";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  reservations: ReservationItem[];
  reservationTotal: number;

  constructor(
    private service: CartService,
    private router: Router,
    private logger: LoggerService,
    public toast: ToastComponent
  ) {}

  ngOnInit(): void {
   this.load();
  }

  load(): void {
    const user = JSON.parse(localStorage.getItem('user'))
    this.service.getReservation(user.id).subscribe(data => {
      console.log("reservation data", data);
      this.reservations = data;
    });
  }

  pay(item: ReservationItem) {
    const user = JSON.parse(localStorage.getItem('user'))

    this.service.payReservation(item.id, user.id).subscribe(data => {
      this.toast.setMessage(data.message, 'success');
      console.log(data);
      this.load();

    }, error => {
      this.toast.setMessage(error.text, 'success');
      console.error(error)
      this.load();
    })
  }
}
