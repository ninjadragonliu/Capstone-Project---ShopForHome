import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:9001/'
  constructor(private http: HttpClient) { }

  // register user
  register(user: User): Observable<User>{
    return this.http.post<User>('${this.apiUrl}/users/register', user);
  }
  
  // other implementation
}
