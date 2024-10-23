import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { OutdoorsComponent } from './outdoors/outdoors.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';


const routes = [
  { path: 'home', component: HomeComponent },
  { path: 'outdoors', component: OutdoorsComponent },
]
@NgModule({
  declarations: [],
  imports: [
    CommonModule, RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
