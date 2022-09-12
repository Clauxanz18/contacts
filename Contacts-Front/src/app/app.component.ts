import { Component } from '@angular/core';
import { Contact } from '../contact'
import { ContactService} from './contact.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Contacts';

  constructor(){

  }
  ngOnInit(){}
  
}




