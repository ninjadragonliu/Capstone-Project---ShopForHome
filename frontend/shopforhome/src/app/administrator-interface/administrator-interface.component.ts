import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-administrator-interface',
  templateUrl: './administrator-interface.component.html',
  styleUrl: './administrator-interface.component.css'
})
export class AdministratorInterfaceComponent {
    users: any[] = [];

    showUsers = true;

    isLoggedIn: boolean = false;
    userRole?: string | null = null;
    constructor(private userService: UserService) {}
    showUserManagement() {
        this.showUsers = true;
        this.userService.getAllUsers().subscribe((users) => {
          this.users = users;
        });
    }
    showProductManagement() {
        this.showUsers = false;
    }
    logout(){
      this.userService.logout();
      this.isLoggedIn = false;
      this.userRole = null;
  
    }
}
