import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../models/cart.model';
import { CartItem } from '../models/cartitem.model';
import { ItemRequest } from '../models/itemrequest.model';

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

  // add product to cart
  addProductToCart(userId:number, productId: number, quantity: number): Observable<CartItem[]>{
    const requestBody: ItemRequest = {productId, quantity};
    return this.http.post<CartItem[]>('${this.apiUrl}/cart/${userId}/items', requestBody);
  }
}
