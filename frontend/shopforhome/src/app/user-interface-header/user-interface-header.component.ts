import { Component } from '@angular/core';
import { UserService, currentUser } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-interface-header',
  templateUrl: './user-interface-header.component.html',
  styleUrl: './user-interface-header.component.css'
})
export class UserInterfaceHeaderComponent {
  isLoggedIn: boolean = false;
  userRole?: string | null = null;
  searchTerm: string = '';

  constructor(private userService: UserService, private router: Router){
    this.isLoggedIn = this.userService.isLoggedIn();
    this.userRole = currentUser.role;
  }

  searchProducts(){
    if(this.searchTerm){
      this.router.navigate(['/products'], { queryParams: {search: this.searchTerm}});
      this.searchTerm = '';
    }
  }

  logout(){
    this.userService.logout();
    this.isLoggedIn = false;
    this.userRole = null;

  }
}
