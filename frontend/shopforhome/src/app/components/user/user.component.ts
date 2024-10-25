import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {
  users: any[] = [];
  selectAll: any;
  selectedUsers: any[] = [];



  constructor (private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((users) => {
      // Add `selected` property to each user
      this.users = users.map(user => ({ ...user, selected: false }));
    });
  }

  toggleSelectAll() {
    this.users.forEach(user => (user.selected = this.selectAll));
  }

  updateSelectAllState() {
    this.selectAll = this.users.every(user => user.selected);
  }

  getSelectedUsers() {
    return this.users.filter(user => user.selected);
  }

  deleteSelectedUsers() {
    this.selectedUsers = this.getSelectedUsers();
    this.selectedUsers.forEach(user => {
      this.userService.deleteUser(user.userId).subscribe();
    });
    this.users = this.users.filter(user => !user.selected);
  }

  updateSelectedUsers() {
    const selectedUsers = this.getSelectedUsers();
    selectedUsers.forEach(user => {
      this.userService.updateUser(user.userId, user).subscribe();
    });
  }
}
