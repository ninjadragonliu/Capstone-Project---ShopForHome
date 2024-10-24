import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';
import { ProductResponse } from '../models/productresponse.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:9005'
  constructor(private http: HttpClient) { }

  // register product
  register(product: Product): Observable<ProductResponse> {
    return this.http.post<ProductResponse>(`${this.apiUrl}/products/register`, product);
  }

  // get products by category
  getProductsByCategory(category: string): Observable<ProductResponse[]>{
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/products/${category}/items`);
  }

  // get all products
  getProducts(): Observable<ProductResponse[]> {
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/products`);
  }

  // get product by product id
  getProductById(productId: number): Observable<ProductResponse> {
    return this.http.get<ProductResponse>(`${this.apiUrl}/products/${productId}`);
  }

  // update product
  updateProduct(productId: number, product: Product): Observable<ProductResponse> {
    return this.http.put<ProductResponse>(`${this.apiUrl}/products/${productId}`, product);
  }

  // delete product
  deleteProduct(productId: number): Observable<String> {
    return this.http.delete<String>(`${this.apiUrl}/products/${productId}`);
  }
}
