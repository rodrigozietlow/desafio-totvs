import { Phone } from './phone.model';

export interface Client {
  id?: number;
  name: string;
  address: string;
  district: string;
  phones: Phone[];
}
