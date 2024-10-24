import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:9005'
  constructor(private http: HttpClient) { }

  // register product
  register(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiUrl}/products/register`, product);
  }

  // get products by category
  getProductsByCategory(category: string): Observable<Product[]>{
    return this.http.get<Product[]>(`${this.apiUrl}/products/${category}/items`);
  }

  // get all products
  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/products`);
  }

  // get product by product id
  getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/products/${productId}`);
  }

  // update product
  updateProduct(productId: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/products/${productId}`, product);
  }

  // delete product
  deleteProduct(productId: number): Observable<String> {
    return this.http.delete<String>(`${this.apiUrl}/products/${productId}`);
  }
}
