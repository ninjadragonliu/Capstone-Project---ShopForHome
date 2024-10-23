import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Wishlist } from '../models/wishlist.model';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private apiUrl = 'http://localhost:9003';
  constructor(private http: HttpClient) { }

  //get wishlist by user
  getWishlistByUser(userId:number): Observable<Wishlist>{
    return this.http.get<Wishlist>('${this.apiUrl}/wishlist/${userId}');
  }

  //additional implementation
}
