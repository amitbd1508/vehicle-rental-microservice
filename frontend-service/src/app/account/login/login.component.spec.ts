import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginComponent } from './login.component';
import { AccountService } from '../account.service';
import { ToastComponent } from '../../shared/components/toast/toast.component';

class AccountServiceMock {}

class RouterMock {}

class ActivatedRouteMock {
  public snapshot = {
    queryParams: {
      returnUrl: '/',
    },
  };
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(
    waitForAsync(() => {
      TestBed.configureTestingModule({
        imports: [FormsModule, ReactiveFormsModule],
        declarations: [LoginComponent],
        providers: [
          FormBuilder,
          ToastComponent,
          { provide: AccountService, useClass: AccountServiceMock },
          { provide: Router, useClass: RouterMock },
          { provide: ActivatedRoute, useClass: ActivatedRouteMock },
        ],
      }).compileComponents();
    })
  );

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display the page header text', () => {
    const el = fixture.debugElement.query(By.css('h4')).nativeElement;
    expect(el.textContent).toContain('Login');
  });

  it('should display the username and password inputs', () => {
    const [inputUsername, inputPassword] = fixture.debugElement.queryAll(
      By.css('input')
    );
    expect(inputUsername.nativeElement).toBeTruthy();
    expect(inputPassword.nativeElement).toBeTruthy();
    expect(inputUsername.nativeElement.value).toBeFalsy();
    expect(inputPassword.nativeElement.value).toBeFalsy();
  });

  it('should display the login button', () => {
    const el = fixture.debugElement.query(By.css('button')).nativeElement;
    expect(el).toBeTruthy();
    expect(el.textContent).toContain('Login');
  });
});
