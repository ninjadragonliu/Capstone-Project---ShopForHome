import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  @ViewChild('f') signupForm!: NgForm;
  userName: string ='';
  password!: string;
  email: string = '';
  
  submitted: boolean = false;
  status: string = 'Registration Successful';

  constructor(private userService: UserService) { 

  }

  onSubmit(): void {
    this.submitted = true;
      this.userName = this.signupForm.value.username;
      this.password = this.signupForm.value.password;
      this.email = this.signupForm.value.email;

      this.userService.register({
        username: this.userName,
        password: this.password,
        email: this.email
      })
      .subscribe((data) => {
        console.log(data);
      });
  
      this.signupForm?.reset();
    // Handle form submission logic here
  }
}

