import { Component, OnInit, Input } from '@angular/core';
import { Injectable } from '@angular/core';
import { Contact } from '../contact';
import {Observable, of} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private contactsUrl = 'http://10.0.0.11:8082/contacts'

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  
  getContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.contactsUrl)
      .pipe(
        tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Contact[]>("Dzzz"))
        );
  }

  getContact(id:number): Observable<Contact> {
    return this.http.get<Contact>(`${this.contactsUrl}/${id}`)
      .pipe(
        tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Contact>("Dzzz"))
        );
  }

  updateContact(contact: Contact, id:number): Observable<any> {
    return this.http.put(`${this.contactsUrl}/${id}` , contact, this.httpOptions).pipe(
      tap(_ => console.log(`updated hero id=${contact.id}`)),
      catchError(this.handleError<any>('updateHero'))
    );
  }

  addContact(contact: Contact): Observable<Contact> {
    return this.http.post<Contact>(this.contactsUrl, contact, this.httpOptions).pipe(
      tap((newContact: Contact) => console.log(`added hero w/ id=${newContact.id}`)),
      catchError(this.handleError<Contact>('addHero'))
    );
  }

  deleteContact(id:number): Observable<Contact> {
    const url = `${this.contactsUrl}/${id}`;
  
    return this.http.delete<Contact>(url, this.httpOptions).pipe(
      tap(_ => console.log(`deleted hero id=${id}`)),
      catchError(this.handleError<Contact>('deleteHero'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
