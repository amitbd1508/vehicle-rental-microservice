import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { By } from '@angular/platform-browser';

import { CartComponent } from './cart.component';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { LoggerService } from '../../shared/service/logger.service';
import { CartService } from '../cart.service';
import { CartItem } from '../../shared/models/cart';

class CartServiceMock {
  getCartItems(): CartItem[] {
    return [];
  }

  calculateCartTotal(cartItems: CartItem[]): number {
    return 0;
  }
}

class RouterMock {}

class LoggerServiceMock {}

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CartComponent],
      providers: [
        ToastComponent,
        { provide: CartService, useClass: CartServiceMock },
        { provide: Router, useClass: RouterMock },
        { provide: LoggerService, useClass: LoggerServiceMock }
      ]
    })
      .compileComponents();
  });


  beforeEach(() => {
    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the page header text', () => {
    const el = fixture.debugElement.query(By.css('h3')).nativeElement;
    expect(el.textContent).toContain('Cart');
  });

  it('should display the checkout button', () => {
    const el = fixture.debugElement.query(By.css('button')).nativeElement;
    expect(el).toBeTruthy();
    expect(el.textContent).toContain('Checkout');
  });
});
