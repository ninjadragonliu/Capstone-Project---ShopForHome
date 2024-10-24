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
  getCartByUser(userId: number): Observable<Cart> {
    return this.http.get<Cart>(`${this.apiUrl}/cart/${userId}`);
  }

  // add product to cart
  addProductToCart(userId: number, productId: number, quantity: number): Observable<CartItem[]> {
    const requestBody: ItemRequest = { productId, quantity };
    return this.http.post<CartItem[]>(`${this.apiUrl}/cart/${userId}/items`, requestBody);
  }

  // remove product from cart
  removeProductFromCart(userId: number, cartItemId: number): Observable<CartItem[]> {
    return this.http.delete<CartItem[]>(`${this.apiUrl}/carts/${userId}/items/${cartItemId}`);
  }

  // update cart item quantity
  updateCartItemQuantity(userId: number, cartItemId: number, quantity: number): Observable<CartItem> {
    return this.http.patch<CartItem>(`${this.apiUrl}/carts/${userId}/items/${cartItemId}`, quantity);
  }

  // clear cart
  clearCart(userId: number): Observable<CartItem[]> {
    return this.http.delete<CartItem[]>(`${this.apiUrl}/carts/${userId}/clear`);
  }
}
