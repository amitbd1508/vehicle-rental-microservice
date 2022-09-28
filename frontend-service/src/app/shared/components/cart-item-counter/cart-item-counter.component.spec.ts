import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartItemCounterComponent } from './cart-item-counter.component';
import { CartService } from '../../../shopping-cart/cart.service';


class CartServiceMock {
  getCartItemsCount(): number {
    return 0;
  }

}

describe('CartItemCounterComponent', () => {
  let component: CartItemCounterComponent;
  let fixture: ComponentFixture<CartItemCounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CartItemCounterComponent],
      providers: [
        { provide: CartService, useClass: CartServiceMock }
      ]
    })
      .compileComponents();
  });


  beforeEach(() => {
    fixture = TestBed.createComponent(CartItemCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
