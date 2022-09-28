import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CartItem } from '../../shared/models/cart';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-cart-item-quantity-change',
  templateUrl: './cart-item-quantity-change.component.html',
  styleUrls: ['./cart-item-quantity-change.component.scss'],
})
export class CartItemQuantityChangeComponent implements OnInit {
  @Input() cartItem: CartItem;
  @Output() quantityChange: EventEmitter<string> = new EventEmitter<string>();

  constructor(private service: CartService) {}

  ngOnInit(): void {}

  decrement(cartItem: CartItem): void {
    if (cartItem.quantity > 1) {
      cartItem.quantity--;
      this.service.updateCart(cartItem);
      this.quantityChange.emit('increment');
    }
  }

  increment(cartItem: CartItem): void {
    if (this.service.canIncreaseQuantity(cartItem)) {
      cartItem.quantity++;
      this.service.updateCart(cartItem);
      this.quantityChange.emit('decrement');
    } else {
      this.quantityChange.emit('failed');
    }
  }
}
