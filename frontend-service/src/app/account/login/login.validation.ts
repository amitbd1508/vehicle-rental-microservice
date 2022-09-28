import { Validators } from '@angular/forms';

export const validationConfig = {
  email: ['', [Validators.required, Validators.email]],
  password: ['', [Validators.required, Validators.minLength(6)]],
};
