import { Component, OnInit, ɵɵqueryRefresh } from '@angular/core';
import { ContactService } from '../contact.service';
import { Contact } from 'src/contact';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  title = 'Contacts';

  constructor(private contactService:ContactService){

  }

  contacts: Contact[];
  contact: Contact;

  ngOnInit(){
    console.log("onInit");
    this.getContacts();
  }

  search(term:string){
    let list:Contact[] = [];
    
    this.contactService.getContacts()
    .subscribe(contacts => {
      list = contacts;
      if(term.trim()){
        this.refresh(list,term.trim());
      }else{
        this.contacts = contacts;
      }
    }
      );
   
  }

  refresh(list:Contact[], term:string){
    let result:Contact[] = []

    result.push(...list.filter(contact => contact.firstName.toUpperCase().indexOf(term.toUpperCase()) >=0 )); 
    result.push(...list.filter(contact => contact.lastName.toUpperCase().indexOf(term.toUpperCase()) >=0 ));

    this.contacts = result;
  }

  getContacts(): void{
    this.contactService.getContacts()
      .subscribe(contacts => this.contacts = contacts);
  }

  getContact(id:number): void {
    this.contactService.getContact(id)
      .subscribe(contact => this.contact = contact);
  }
}
