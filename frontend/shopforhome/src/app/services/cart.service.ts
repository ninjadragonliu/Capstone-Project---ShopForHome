import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:9003/';
  constructor(private http: HttpClient) { }

  // get cart by user
  getCartByUser(userId:number): Observable<Cart>{
    return this.http.get<Cart>('${this.apiUrl}/cart/${userId}');
  }

  //additional implementation
}
