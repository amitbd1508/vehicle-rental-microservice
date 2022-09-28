import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { CartItem } from '../../shared/models/cart';
import { ProductService } from '../../product/product.service';
import { Router } from '@angular/router';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { LoggerService } from '../../shared/service/logger.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
})
export class CartComponent implements OnInit {
  cartItems: CartItem[];
  cartTotal: number;

  constructor(
    private service: CartService,
    private router: Router,
    private logger: LoggerService,
    public toast: ToastComponent
  ) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.cartItems = this.service.getCartItems();
    this.cartTotal = this.service.calculateCartTotal(this.cartItems);
  }

  checkOut(): void {
    this.service.checkout(this.service.getCartItems()).subscribe(
      (it) => {
        this.toast.setMessage(`You have placed an order of TK ${it.totalPrice}`, 'warning', 3000);

        this.service.clearCart();
        this.router.navigate(['/product']);
      },
      (error) => {
        this.toast.setMessage(`${error.error}`, 'danger');
        this.logger.logError('LoginComponent', error.error);
      }
    );
  }

  removeItem(cartItem: CartItem): void {
    this.service.removeCartItem(cartItem);
    this.loadCart();
  }

  onChangeQuantity($event: string): void {
    if ($event !== 'failed') {
      this.loadCart();
    } else {
      this.toast.setMessage(
        'Cannot increase the quantity of this product!',
        'danger'
      );
    }
  }
}
