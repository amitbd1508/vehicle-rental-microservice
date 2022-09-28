import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class LoggerService {
  constructor() {}

  log(location: string, message: string): void {
    if (!environment.production) {
      console.log(`${location}: ${message}`);
    }
  }

  logError(location: string, error: string): void {
    if (!environment.production) {
      console.error(`${location}: ${error}`);
    }
  }
}
