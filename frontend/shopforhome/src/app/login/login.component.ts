import { Component, ViewChild } from '@angular/core';
import { UserService } from '../services/user.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  @ViewChild('f') loginForm!: NgForm
  userName: string = ''
  password: string = ''
  submitted: boolean = false
  status: string = 'Login Successful'
  constructor(private userService: UserService) { }
  onSubmit() {
    this.userName = this.loginForm.value.username
    this.password = this.loginForm.value.password
    this.userService.login(this.userName, this.password).subscribe((data) => {
      console.log(data)
    })
    this.submitted = true
    this.loginForm.reset()
  }
}
