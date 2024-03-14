import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientService } from './client.service';
import {
  AsyncValidatorFn,
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDividerModule } from '@angular/material/divider';
import { phoneExists } from './phone-exists.validator';
import { phoneFormatValidator } from './phone-format.validator';
import { Client } from './client.model';
import { BehaviorSubject, from, map, mergeMap, of, switchMap } from 'rxjs';
import { PhoneService } from './phone.service';
import { clientExists } from './client-exists.validator';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatTooltipModule,
    MatDividerModule,
  ],
  providers: [phoneExists, clientExists],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  form = this.createForm();

  get phones() {
    return this.form.controls.phones;
  }

  clients$ = new BehaviorSubject<Client[]>([]);

  constructor(
    private _clientService: ClientService,
    private _phoneService: PhoneService,
    private _fb: FormBuilder,
    private _phoneExists: phoneExists,
    private _clientExists: clientExists
  ) {
    this.reloadClientList();
  }

  private reloadClientList() {
    this._clientService.listClients().subscribe({
      next: (v) => this.clients$.next(v),
      error: (e) => this.clients$.error(e),
    });
  }

  private newFormPhone() {
    return this._fb.group({
      number: [
        '',
        [
          phoneFormatValidator(
            /^\([1-9]{2}\) (?:[2-8]|9[0-9])[0-9]{3}\-[0-9]{4}$/
          ),
        ],
        [this._phoneExists],
      ],
    });
  }

  private createForm() {
    return this._fb.group({
      name: [
        '',
        [Validators.required, Validators.minLength(10)],
        [this._clientExists],
      ],
      address: '',
      district: '',
      phones: this._fb.array([this.newFormPhone()]),
    });
  }

  addPhone() {
    this.form.controls.phones.push(this.newFormPhone());
  }

  deletePhone(index: number) {
    this.form.controls.phones.removeAt(index);
  }

  errorPhoneMessage(phone: FormControl) {
    if (phone.errors?.['format']) {
      return 'Formato inválido! Por favor, digite no formato (XX) 9XXXX-XXXX';
    } else if (phone.errors?.['exists']) {
      return 'Já existe este telefone cadastrado para outro cliente';
    }

    return '';
  }

  errorClientMessage(client: FormControl) {
    if (client.errors?.['required'] || client.errors?.['minlength']) {
      return 'Mínimo 10 caracteres';
    } else if (client.errors?.['exists']) {
      return 'Já existe este um cliente com esse nome';
    }

    return '';
  }

  save() {
    if (!this.form.valid) {
      return;
    }

    const formValue = this.form.getRawValue();
    const { phones, ...client } = formValue as unknown as Client;

    this._clientService
      .addClient(client as Client)
      .pipe(
        mergeMap((client) =>
          phones.length > 0
            ? from(phones).pipe(
                map((phone) => ({
                  ...phone,
                  owner: { id: client.id } as Client,
                })),
                switchMap((phone) => this._phoneService.addPhone(phone))
              )
            : of(null)
        )
      )
      .subscribe(() => {
        this.form = this.createForm();
        this.reloadClientList();
      });
  }
}
