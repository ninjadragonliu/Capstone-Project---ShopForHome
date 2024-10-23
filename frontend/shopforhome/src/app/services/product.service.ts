import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:9005/'
  constructor(private http: HttpClient) { }

  // register product
  register(product: Product): Observable<Product>{
    return this.http.post<Product>('${this.apiUrl}/products/register', product);
  }

  // get all
  getProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(this.apiUrl);
  }

  // need to implement add/update/delete
}
