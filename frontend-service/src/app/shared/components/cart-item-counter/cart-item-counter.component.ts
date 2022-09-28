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
    this.handleSubscription();
    this.updateCartCount();
  }

  handleSubscription(): void {
    this.messengerService.getCartChangeNotification().subscribe((payload) => {
      this.updateCartCount();
    });
  }

  updateCartCount(): void {
    this.currentCartSize = this.cartService.getCartItemsCount();
  }
}
