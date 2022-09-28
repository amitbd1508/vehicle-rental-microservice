import { FormGroup, Validators } from '@angular/forms';

export const validationConfig = {
  firstname: ['', [Validators.required, Validators.min(4), Validators.max(100)]],
  lastname: ['', [Validators.required, Validators.min(4), Validators.max(100)]],
  username: ['', [Validators.required, Validators.min(4), Validators.max(100)]],
  email: ['', [Validators.required, Validators.email]],
  password: ['', [Validators.required, Validators.minLength(6)]],
  confirmation: ['', [Validators.required, Validators.minLength(6)]],
};

export function mustMatch(
  controlName: string,
  matchingControlName: string
): any {
  return (formGroup: FormGroup) => {
    const control = formGroup.controls[controlName];
    const matchingControl = formGroup.controls[matchingControlName];

    if (matchingControl.errors && !matchingControl.errors.mustMatch) {
      // return if another validator has already found an error on the matchingControl
      return;
    }

    // set error on matchingControl if validation fails
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ mustMatch: true });
    } else {
      matchingControl.setErrors(null);
    }
  };
}
