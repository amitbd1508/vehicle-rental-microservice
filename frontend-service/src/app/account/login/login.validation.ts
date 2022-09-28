import { Validators } from '@angular/forms';

export const validationConfig = {
  username: ['', [Validators.required]],
  password: ['', [Validators.required]],
};
