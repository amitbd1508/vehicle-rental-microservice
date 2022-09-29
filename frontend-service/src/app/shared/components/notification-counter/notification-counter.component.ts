import { Component, OnInit } from '@angular/core';
import {TimerObservable} from "rxjs-compat/observable/TimerObservable";
import {MessengerService} from "../../service/messenger.service";

@Component({
  selector: 'app-notification-counter',
  templateUrl: './notification-counter.component.html',
  styleUrls: ['./notification-counter.component.css']
})
export class NotificationCounterComponent implements OnInit {
  currentNotificationSize = 0;

  constructor(private svc: MessengerService) { }

  ngOnInit(): void{
    const user = JSON.parse(localStorage.getItem('user'))
    TimerObservable.create(0, 2000)
      .subscribe(() => {
        console.log("scheduler is running")
        this.svc.getNotificationCount(user.id).subscribe(data => {
          console.log("data", data)
          this.currentNotificationSize = data;
        })
      });
  }

}
