import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { CartComponent } from './cart/cart.component';
import { FormsModule } from '@angular/forms';
import { ShoppingCartRoutingModule } from './shopping-cart-routing.module';
import { CartItemQuantityChangeComponent } from './cart-item-quantity-change/cart-item-quantity-change.component';

@NgModule({
  declarations: [CartComponent, CartItemQuantityChangeComponent],
  imports: [CommonModule, SharedModule, ShoppingCartRoutingModule, FormsModule]
})
export class ShoppingCartModule {}
