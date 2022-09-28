import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {CurrentUser} from "../models/user";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class MessengerService {
  cartSubject = new Subject();

  constructor(private http: HttpClient) {}

  updateCart(): void {
    this.cartSubject.next('update-cart');
  }

  getCartChangeNotification(): Observable<any> {
    return this.cartSubject.asObservable();
  }

  getNotificationCount(): Observable<any> {
    return this.http.get(`${environment.notificationsUrl}/notifications/count`);
  }


}
