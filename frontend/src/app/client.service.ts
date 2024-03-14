import { Injectable } from '@angular/core';
import { env } from './environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from './client.model';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private _http: HttpClient) {}

  listClients(): Observable<Client[]> {
    return this._http.get<Client[]>(`${env.endpoint}/client`);
  }

  addClient(client: Client): Observable<Client> {
    return this._http.post<Client>(`${env.endpoint}/client`, client);
  }

  clientNameExists(client: Client): Observable<boolean> {
    return this._http.get<boolean>(
      `${env.endpoint}/client/validate/` + client.name
    );
  }
}
