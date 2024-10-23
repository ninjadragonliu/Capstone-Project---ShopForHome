import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OutdoorsComponent } from './outdoors/outdoors.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { AdministratorInterfaceComponent } from './administrator-interface/administrator-interface.component';
import { LoginComponent } from './login/login.component';


const routes = [
  { path: 'admin', component: AdministratorInterfaceComponent },
  { path: '', component: HomeComponent },
  { path: 'outdoors', component: OutdoorsComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule, RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
