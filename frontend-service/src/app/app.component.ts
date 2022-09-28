import { Component, OnInit } from '@angular/core';
import { AccountService } from './account/account.service';
import { LoggerService } from './shared/service/logger.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  constructor(
    private accountService: AccountService,
    private loggerService: LoggerService
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser();
  }

  loadCurrentUser(): void {
    const token = localStorage.getItem('token');
    this.accountService.loadCurrentUser(token).subscribe(
      () => {},
      (error) => this.loggerService.logError('AppComponent', error.error)
    );
  }
}
