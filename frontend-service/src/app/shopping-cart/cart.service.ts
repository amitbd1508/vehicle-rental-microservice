import { Injectable } from '@angular/core';
import { CartItem, CheckOutResponse } from '../shared/models/cart';
import { MessengerService } from '../shared/service/messenger.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  /*
   Fixme: Need to maintain the cart functionality in server side.
   Due to time constraint i am skipping this feature by temporary hack.
   It only works if product inventory is not decease/increase by any other order or user
   */

  constructor(private messengerService: MessengerService,
              private http: HttpClient
  ) {}

  getCartItems(): CartItem[] {
    const cartItems: CartItem[] = JSON.parse(localStorage.getItem('cartItems'));
    return cartItems ? cartItems : [];
  }

  getCartItemsCount(): number {
    const cartItems: CartItem[] = JSON.parse(localStorage.getItem('cartItems'));
    return cartItems ? cartItems.length : 0;
  }

  addProductToCart(cartItem: CartItem): boolean {
    let currentCart: CartItem[] = JSON.parse(localStorage.getItem('cartItems'));
    if (!currentCart) {
      currentCart = [];
    }
    currentCart.push(cartItem);
    localStorage.setItem('cartItems', JSON.stringify(currentCart));
    this.messengerService.updateCart();

    return true;
  }

  calculateCartTotal(cartItems: CartItem[]): number {
    let cartTotal = 0;
    cartItems.forEach(item => {
      cartTotal += (item.quantity * item.productPrice);
    });

    return cartTotal;
  }

  updateCart(updatedCartItem: CartItem): boolean {
    const currentCart: CartItem[] = JSON.parse(localStorage.getItem('cartItems'));

    for (let i = 0; i < currentCart.length; i++) {
      if (currentCart[i].id === updatedCartItem.id) {
        currentCart[i] = updatedCartItem;
        localStorage.setItem('cartItems', JSON.stringify(currentCart));
        this.messengerService.updateCart();
        return true;
      }
    }
    return false;
  }

  canIncreaseQuantity(cartItem: CartItem): boolean {
    return cartItem.quantity < cartItem.numberOfAvailableProduct;
  }

  removeCartItem(cartItem: CartItem): void {
    const currentCart: CartItem[] = JSON.parse(localStorage.getItem('cartItems'));

    for (let i = 0; i < currentCart.length; i++) {
      if (currentCart[i].id === cartItem.id) {
        currentCart.splice(i, 1);
      }
    }

    localStorage.setItem('cartItems', JSON.stringify(currentCart));
    this.messengerService.updateCart();
  }

  clearCart(): void {
    localStorage.removeItem('cartItems');
    this.messengerService.updateCart();
  }

  checkout(cart: CartItem[]): Observable<CheckOutResponse> {
    return this.http.post<CheckOutResponse>(`/api/v1/checkout`, cart);
  }
}
