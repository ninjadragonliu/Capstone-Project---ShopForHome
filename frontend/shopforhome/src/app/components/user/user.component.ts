import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  users: any[] = [];

  showUsers = false;

  constructor (private userService: UserService) { }
  showUserManagement() {
    this.showUsers = true;
    this.userService.getAllUsers().subscribe((users) => {
      this.users = users;
    });
}
}
