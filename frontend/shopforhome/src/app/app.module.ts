import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { OutdoorsComponent } from './outdoors/outdoors.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { AdministratorInterfaceComponent } from './administrator-interface/administrator-interface.component';
import { UserInterfaceComponent } from './user-interface/user-interface.component';


@NgModule({
  declarations: [
    AppComponent, OutdoorsComponent, HomeComponent, SignupComponent, AdministratorInterfaceComponent, UserInterfaceComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }


