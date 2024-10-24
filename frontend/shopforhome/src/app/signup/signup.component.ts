import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  loginSuccess: boolean | null = null;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private userService: UserService) { }

  onSubmit(form: NgForm) {
    if (form.valid) {
      const { username, email, password } = form.value;
      const user: User = {username, email, password};
      this.userService.register(user).subscribe({
        next: response => {
          this.loginSuccess = true;
          this.successMessage = 'Registration Success!';
          this.errorMessage = null;
          form.reset();
        },
        error: err => {
          this.loginSuccess = false;
          this.errorMessage = 'Registration failed.';
          this.successMessage = null;
        }
      });
    }
  }
}
