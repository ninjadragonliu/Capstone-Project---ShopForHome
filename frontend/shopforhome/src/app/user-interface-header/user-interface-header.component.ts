import { Component } from '@angular/core';
import { UserService, currentUser } from '../services/user.service';

@Component({
  selector: 'app-user-interface-header',
  templateUrl: './user-interface-header.component.html',
  styleUrl: './user-interface-header.component.css'
})
export class UserInterfaceHeaderComponent {
  isLoggedIn: boolean = false;
  userRole?: string | null = null;

  constructor(private userService: UserService){
    this.isLoggedIn = this.userService.isLoggedIn();
    this.userRole = currentUser.role;
  }

  logout(){
    this.userService.logout();
    this.isLoggedIn = false;
    this.userRole = null;

  }
}
