import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginSuccess: boolean | null = null;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private userService: UserService) { }

  onSubmit(form: NgForm) {
    if (form.valid) {
      const { username, password } = form.value;
      this.userService.login(username, password).subscribe({
        next: response => {
          this.loginSuccess = true;
          this.successMessage = 'Login Success!';
          this.errorMessage = null;
          form.reset();
        },
        error: err => {
          this.loginSuccess = false;
          this.errorMessage = 'Login failed. Please check your username or password.';
          this.successMessage = null;
        }
      });
    }
  }
}
