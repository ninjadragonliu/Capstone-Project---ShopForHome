import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../models/order.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:9020';
  constructor(private http: HttpClient) { }

  // register product
  register(order: Order): Observable<Order> {
    return this.http.post<Order>('${this.apiUrl}/orders/register', order);
  }
}
