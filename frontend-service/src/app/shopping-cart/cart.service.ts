import { Injectable } from '@angular/core';
import { CartItem, CheckOutResponse } from '../shared/models/cart';
import { MessengerService } from '../shared/service/messenger.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {}

  getReservation(userId: string): Observable<any> {
    return this.http.get<any>(`${environment.reservationsUrl}/getUserReservation/${userId}`);
  }

  payReservation(reservationId: string, userId: string): Observable<any> {
    return this.http.post<any>(`${environment.reservationsUrl}/pay`, {
      userId: userId,
      reservationId: reservationId
    });
  }

}
