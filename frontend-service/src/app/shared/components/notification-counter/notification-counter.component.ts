import { Component, OnInit } from '@angular/core';
import {TimerObservable} from "rxjs-compat/observable/TimerObservable";

@Component({
  selector: 'app-notification-counter',
  templateUrl: './notification-counter.component.html',
  styleUrls: ['./notification-counter.component.css']
})
export class NotificationCounterComponent implements OnInit {
  currentNotificationSize = 0;

  constructor() { }

  ngOnInit(): void{
    TimerObservable.create(0, 1000)
      .subscribe(() => {
        console.log("scheduler is running")

      });
  }

}
