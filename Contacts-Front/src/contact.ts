import {Telephone } from './telephone'

export interface Contact {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    favorited: boolean;
    telephones: [Telephone];
  }