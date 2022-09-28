import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MessengerService {
  cartSubject = new Subject();

  constructor() {}

  updateCart(): void {
    this.cartSubject.next('update-cart');
  }

  getCartChangeNotification(): Observable<any> {
    return this.cartSubject.asObservable();
  }
}
