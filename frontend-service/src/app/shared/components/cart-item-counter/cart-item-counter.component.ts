import { Component, OnInit } from '@angular/core';
import { MessengerService } from '../../service/messenger.service';
import { CartService } from '../../../shopping-cart/cart.service';

@Component({
  selector: 'app-cart-item-counter',
  templateUrl: './cart-item-counter.component.html',
  styleUrls: ['./cart-item-counter.component.scss'],
})
export class CartItemCounterComponent implements OnInit {
  currentCartSize = 0;

  constructor(
    private messengerService: MessengerService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
  }

}
