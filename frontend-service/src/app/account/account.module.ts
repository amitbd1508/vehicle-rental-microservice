import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { AccountRoutingModule } from './account-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AccountService } from './account.service';

@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [CommonModule, AccountRoutingModule, SharedModule]
})
export class AccountModule {}
