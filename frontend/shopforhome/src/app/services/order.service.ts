import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../models/order.model';
import { Observable } from 'rxjs';
import { OrderItem } from '../models/orderitem.model';
import { OrderResponse } from '../models/orderresponse.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:9020';
  constructor(private http: HttpClient) { }

  // register order
  register(order: Order): Observable<OrderResponse> {
    return this.http.post<OrderResponse>(`${this.apiUrl}/orders/register`, order);
  }

  // get order by order id
  getOrderById(orderId: number): Observable<OrderResponse>{
    return this.http.get<OrderResponse>(`${this.apiUrl}/orders/${orderId}`);
  }

  // update order
  updateOrder(orderId: number, orderItem: OrderItem): Observable<OrderResponse>{
    return this.http.put<OrderResponse>(`${this.apiUrl}/orders/${orderId}`, orderItem);
  }

  // complete order
  completeOrder(orderId: number): Observable<OrderResponse>{
    return this.http.get<OrderResponse>(`${this.apiUrl}/orders/${orderId}/complete`);
  }

  // cancel order
  cancelOrder(orderId: number): Observable<string>{
    return this.http.delete<string>(`${this.apiUrl}/orders/${orderId}`);
  }

  // delete order
  deleteOrder(orderId: number): Observable<string>{
    return this.http.delete<string>(`${this.apiUrl}/orders/${orderId}/delete`);
  }
}
