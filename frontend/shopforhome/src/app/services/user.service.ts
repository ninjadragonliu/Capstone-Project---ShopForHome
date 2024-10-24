import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { User } from '../models/user.model';
import { LoginRequest } from '../models/loginrequest.model';
import { UserResponse } from '../models/userresponse.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  apiUrl = 'http://localhost:9001';
  loggedIn: boolean = false;
  currentUser: UserResponse | null = null;
  constructor(private http: HttpClient) { }

  // login user
  login(username: string, password: string): Observable<UserResponse> {
    const requestBody: LoginRequest = { username, password };
    return this.http.post<UserResponse>(`${this.apiUrl}/users/login`, requestBody).pipe(
      tap( response =>{
        this.currentUser = response;
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
    return this.currentUser?.role;
  }

  // get user id
  getUserId(){
    return this.currentUser?.userId;
  }

  // logout
  logout(){
    this.setLoginStatus(false);
  }

  // check login status
  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  // register user
  register(user: User): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiUrl}/users/register`, user);
  }

  // get user by id
  getUserById(userId: number): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.apiUrl}/users/${userId}`);
  }

  // update user
  updateUser(userId: number, user: User): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.apiUrl}/users/${userId}`, user);
  }

  // delete user
  deleteUser(userId: number): Observable<String> {
    return this.http.delete<String>(`${this.apiUrl}/users/${userId}`);
  }
}
