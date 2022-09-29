import { Injectable } from '@angular/core';
import { CurrentUser } from '../shared/models/user';
import { Observable, of, ReplaySubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MessengerService } from '../shared/service/messenger.service';
import { environment } from '../../environments/environment';

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
    return this.http.post(`${environment.accountsUrl}/register`, values).pipe(
      map((user: CurrentUser) => {
        if (user) {
          localStorage.setItem('token', user.accessToken);
          localStorage.setItem('user', JSON.stringify(user));
          this.currentUserSource.next(user);
        }
      })
    );
  }

  login(values: any): Observable<any> {
    return this.http.post(`${environment.accountsUrl}/login`, values).pipe(
      map((user: any) => {
        if (user) {
          localStorage.setItem('token', user.accessToken);
          localStorage.setItem('user', JSON.stringify(user));
          this.currentUserSource.next(user);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('cartItems');
    localStorage.removeItem('user');
    this.currentUserSource.next(null);
    this.messengerService.updateCart();
    this.router.navigateByUrl('/account/login');
  }

  loadCurrentUser(token: string): Observable<any> {
    if (token === null) {
      this.currentUserSource.next(null);
      return of(null);
    }

    this.currentUserSource.next(JSON.parse(localStorage.getItem('user')));

    return this.currentUserSource;
    // let headers = new HttpHeaders();
    // headers = headers.set('Authorization', `Bearer ${token}`);
    //
    // return this.http.get('/api/v1/account', { headers }).pipe(
    //   map((user: CurrentUser) => {
    //     if (user) {
    //       localStorage.setItem('token', user.token);
    //       this.currentUserSource.next(user);
    //     }
    //   })
    // );
  }
}
