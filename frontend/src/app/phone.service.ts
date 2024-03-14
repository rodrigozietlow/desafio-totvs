import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, delay, map, of } from 'rxjs';
import { Phone } from './phone.model';
import { env } from './environment';

@Injectable({
  providedIn: 'root',
})
export class PhoneService {
  constructor(private _http: HttpClient) {}

  clientHasPhone(phone: Phone): Observable<boolean> {
    return this._http.get<boolean>(
      `${env.endpoint}/phone/validate/` + phone.number
    );
  }

  addPhone(phone: Phone): Observable<any> {
    return this._http.post(`${env.endpoint}/phone`, phone);
  }
}
