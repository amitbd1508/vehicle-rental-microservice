import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { validationConfig } from './login.validation';
import { AccountService } from '../account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoggerService } from '../../shared/service/logger.service';
import { ToastComponent } from '../../shared/components/toast/toast.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  submitted = false;

  returnUrl: string;

  constructor(
    private fb: FormBuilder,
    private service: AccountService,
    private router: Router,
    private logger: LoggerService,
    public toast: ToastComponent,
    private activatedRoute: ActivatedRoute
  ) {
    this.buildForm();
  }

  // convenience getter for easy access to form fields
  get f(): any {
    return this.form.controls;
  }

  ngOnInit(): void {
    this.returnUrl = this.activatedRoute.snapshot.queryParams.returnUrl || '/';
  }

  buildForm(): void {
    this.form = this.fb.group(validationConfig);
  }

  onLogin(): void {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.service.login(this.form.value).subscribe(
      () => {
        this.router.navigateByUrl(this.returnUrl);
      },
      (error) => {
        this.toast.setMessage(`${error.error}`, 'danger');
        this.logger.logError('LoginComponent', error.error);
      }
    );
  }
}
