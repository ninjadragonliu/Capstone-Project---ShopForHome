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

  selectedUser: any = null;


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

  updateUser() {
    const selectedUsers = this.getSelectedUsers();
    if (selectedUsers.length === 1) {
      this.selectedUser = { ...selectedUsers[0] }; // Create a copy of the selected product for editing
    } else {
      alert("Please select exactly one product to update.");
    }
  }

  onUpdateUserSubmit() {
    if (this.selectedUser) {
      this.userService.updateUser(this.selectedUser.userId, this.selectedUser).subscribe(updatedUser => {
        const index = this.users.findIndex(p => p.productId === updatedUser.userId);
        this.users[index] = updatedUser;
        this.selectedUser = null; // Clear the selection
      });
    }
  }

  cancelEdit() {
    this.selectedUser = null;
  }
}
