import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactDetailsComponent } from './contact-details/contact-details.component';
import { ContactListComponent } from './contact-list/contact-list.component'

const routes: Routes = [
  { path: 'details/:id', component: ContactDetailsComponent},
  { path: 'details', component: ContactDetailsComponent},
  { path: 'contacts', component: ContactListComponent},

  { path: '', redirectTo: 'contacts',pathMatch:'full'}
  
];

@NgModule({
  exports: [RouterModule],
  imports: [
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
