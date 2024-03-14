import { Injectable } from '@angular/core';
import {
  AbstractControl,
  AsyncValidator,
  ValidationErrors,
} from '@angular/forms';
import { Observable, catchError, map, of, switchMap, timer } from 'rxjs';
import { Client } from './client.model';
import { ClientService } from './client.service';

@Injectable()
export class clientExists implements AsyncValidator {
  constructor(private service: ClientService) {}
  validate(
    control: AbstractControl<any, any>
  ): Observable<ValidationErrors | null> {
    // o angular cancela o observable se acontece outro pedido
    // de validação, então o timer funciona como debounce
    return timer(500).pipe(
      switchMap(() =>
        this.service.clientNameExists({ name: control.value } as Client)
      ),
      map((exists) => (exists ? { exists: { value: control.value } } : null)),
      catchError(() => of(null))
    );
  }
}
