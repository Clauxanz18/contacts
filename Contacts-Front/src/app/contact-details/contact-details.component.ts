import { Component, OnInit, Input,ViewChild, ElementRef  } from '@angular/core';
import { ActivatedRoute} from '@angular/router';
import { ContactService} from '../contact.service';
import { Location} from '@angular/common';
import { Contact } from '../../contact'
import {Telephone} from  '../../telephone'
import { NullTemplateVisitor } from '@angular/compiler';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.css']
})
export class ContactDetailsComponent implements OnInit {

  @ViewChild('alert', { static: true }) alert: ElementRef;



  constructor(
    private route: ActivatedRoute,
    private contactService: ContactService,
    private location: Location,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.getContact();
    
  }

  removePhone(phone:Telephone){
    const index = this.contact.telephones.indexOf(phone);
      if (index > -1) {
      this.contact.telephones.splice(index, 1);
    }
    this.toastr.warning('The Phone number was removed','Success');
  }

  addPhone(){
    let telephone:Telephone = {
      number: "",
      type:""
    }
    this.contact.telephones.push(telephone);
  }

  getContact(): void {
    const id = +this.route.snapshot.paramMap.get('id');

    if(id > 0){
      this.contactService.getContact(id)
      .subscribe(contact => this.contact = contact);
    }else{
      let telephone: Telephone ={
        number: null,
        type: null
      }
      let newcontact: Contact = {
        id : 0,
        firstName : null,
        lastName : null,
        email : null,
        favorited: false,
        telephones: [telephone]
      }
      this.contact = newcontact;
    }
    
  }



  goBack(): void {
    this.location.back();
  }

  save(): void {
    if(this.contact.id>0){
      console.log(this.contact.firstName);
      console.log(this.contact.firstName.trim());
      this.contactService.updateContact(this.contact, this.contact.id)
      .subscribe(() => {
        this.ngOnInit();
        this.toastr.success('Success', 'Contact was saved Successfully');
      }
      );
    }else if(this.contact.firstName){
      this.contactService.addContact(this.contact)
        .subscribe(() => this.ngOnInit());
    }
  }

  delete(id:number): void {
    this.contactService.deleteContact(id)
      .subscribe(() => {
        this.toastr.warning('Contact was Deleted Successfully','Success');
        this.location.back();
      }
        );
  }

  create(): void {

  }

  @Input() contact: Contact;

}
