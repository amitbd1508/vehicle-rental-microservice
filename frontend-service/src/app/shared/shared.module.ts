import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CartItemCounterComponent } from './components/cart-item-counter/cart-item-counter.component';
import { ToastComponent } from './components/toast/toast.component';
import { LoadingComponent } from './components/loading/loading.component';
import { NotificationCounterComponent } from './components/notification-counter/notification-counter.component';

@NgModule({
  declarations: [CartItemCounterComponent, ToastComponent, LoadingComponent, NotificationCounterComponent],
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RouterModule],
    exports: [
        ReactiveFormsModule,
        CartItemCounterComponent,
        ToastComponent,
        LoadingComponent,
        NotificationCounterComponent,
    ],
  providers: [ToastComponent],
})
export class SharedModule {}
