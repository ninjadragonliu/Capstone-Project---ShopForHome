import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Wishlist } from '../models/wishlist.model';
import { WishlistItem } from '../models/wishlistitem.model';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private apiUrl = 'http://localhost:9003';
  constructor(private http: HttpClient) { }

  //get wishlist by user
  getWishlistByUser(userId: number): Observable<Wishlist> {
    return this.http.get<Wishlist>('${this.apiUrl}/wishlist/${userId}');
  }

  // add product to wishlist
  addProductToWishlist(userId: number, productId: number): Observable<WishlistItem[]> {
    return this.http.post<WishlistItem[]>(`${this.apiUrl}/wishlist/${userId}/items`, productId);
  }

  // remove product from wishlist
  removeProductFromWishlist(userId: number, wishlistItemId: number): Observable<WishlistItem[]> {
    return this.http.delete<WishlistItem[]>(`${this.apiUrl}/wishlist/${userId}/items/${wishlistItemId}`);
  }

  // clear wishlist
  clearCart(userId: number): Observable<WishlistItem[]> {
    return this.http.delete<WishlistItem[]>(`${this.apiUrl}/wishlist/${userId}/clear`);
  }
}
