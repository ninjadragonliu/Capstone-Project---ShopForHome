import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OutdoorsComponent } from './outdoors/outdoors.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { UserInterfaceComponent } from './user-interface/user-interface.component';
import { AdministratorInterfaceComponent } from './administrator-interface/administrator-interface.component';


const routes = [
  { path: 'admin', component: AdministratorInterfaceComponent},
  { path: '', component: HomeComponent },
  { path: 'outdoors', component: OutdoorsComponent },
  { path: 'signup', component: SignupComponent }
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule, RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
