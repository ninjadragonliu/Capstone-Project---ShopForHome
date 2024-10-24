import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { User } from '../models/user.model';
import { LoginRequest } from '../models/loginrequest.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = 'http://localhost:9001';
  loggedIn: boolean = false;
  userRole: string | null = null;
  constructor(private http: HttpClient) { }

  // login user
  login(username: string, password: string): Observable<User> {
    const requestBody: LoginRequest = { username, password };
    return this.http.post<User>(`${this.apiUrl}/users/login`, requestBody).pipe(
      tap( response =>{
        this.userRole = response.role;
        this.setLoginStatus(true);
      })
    );
  }

  // set login status
  setLoginStatus(isLoggedIn: boolean) {
    this.loggedIn = isLoggedIn;
  }

  // get role
  getUserRole(){
    return this.userRole;
  }

  // logout
  logout(){
    this.setLoginStatus(false);
    this.userRole = null;
  }

  // check login status
  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  // register user
  register(user: User): Observable<User> {
    this.userRole = user.role;
    return this.http.post<User>(`${this.apiUrl}/users/register`, user);
  }

  // get user by id
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/users/${userId}`);
  }

  // update user
  updateUser(userId: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/users/${userId}`, user);
  }

  // delete user
  deleteUser(userId: number): Observable<String> {
    return this.http.delete<String>(`${this.apiUrl}/users/${userId}`);
  }
}
