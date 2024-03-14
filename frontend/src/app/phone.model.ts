import { Client } from './client.model';

export interface Phone {
  id?: number;
  number: string;
  owner?: Client;
}
