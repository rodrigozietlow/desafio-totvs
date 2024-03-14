import { Injectable } from '@angular/core';
import {
  AbstractControl,
  AsyncValidator,
  ValidationErrors,
} from '@angular/forms';
import { Observable, catchError, map, of } from 'rxjs';
import { Phone } from './phone.model';
import { PhoneService } from './phone.service';

@Injectable()
export class phoneExists implements AsyncValidator {
  constructor(private service: PhoneService) {}
  validate(
    control: AbstractControl<any, any>
  ): Observable<ValidationErrors | null> {
    return this.service.clientHasPhone({ number: control.value } as Phone).pipe(
      map((exists) => (exists ? { exists: { value: control.value } } : null)),

      catchError(() => of(null))
    );
  }
}
