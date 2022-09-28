import { Injectable } from '@angular/core';
import { CurrentUser } from '../shared/models/user';
import { Observable, of, ReplaySubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MessengerService } from '../shared/service/messenger.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private currentUserSource = new ReplaySubject<CurrentUser>(1);
  currentUser$ = this.currentUserSource.asObservable();

  constructor(
    private http: HttpClient,
    private messengerService: MessengerService,
    private router: Router
  ) {}

  register(values: any): Observable<void> {
    return this.http.post('/api/v1/register', values).pipe(
      map((user: CurrentUser) => {
        if (user) {
          localStorage.setItem('token', user.token);
          this.currentUserSource.next(user);
        }
      })
    );
  }

  login(values: any): Observable<any> {
    return this.http.post('/api/v1/login', values).pipe(
      map((user: CurrentUser) => {
        if (user) {
          localStorage.setItem('token', user.token);
          this.currentUserSource.next(user);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('cartItems');
    this.currentUserSource.next(null);
    this.messengerService.updateCart();
    this.router.navigateByUrl('/account/login');
  }

  loadCurrentUser(token: string): any {
    if (token === null) {
      this.currentUserSource.next(null);
      return of(null);
    }

    let headers = new HttpHeaders();
    headers = headers.set('Authorization', `Bearer ${token}`);

    return this.http.get('/api/v1/account', { headers }).pipe(
      map((user: CurrentUser) => {
        if (user) {
          localStorage.setItem('token', user.token);
          this.currentUserSource.next(user);
        }
      })
    );
  }
}
