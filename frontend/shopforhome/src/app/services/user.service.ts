import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { LoginRequest } from '../models/loginrequest.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:9001';
  constructor(private http: HttpClient) { }

  // login user
  login(username: string, password: string): Observable<User> {
    const requestBody: LoginRequest = { username, password };
    return this.http.post<User>(`${this.apiUrl}/users/register`, requestBody)
  }

  // register user
  register(user: User): Observable<User> {
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
