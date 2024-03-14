import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function phoneFormatValidator(number: RegExp): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const validFormat = number.test(control.value);
    return !validFormat ? { format: { value: control.value } } : null;
  };
}
