import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';
import { ProductResponse } from '../models/productresponse.model';

0.
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

  // get products by name
  getProductsByName(name: string): Observable<ProductResponse[]>{
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/products/name/${name}`);
  }

  // get products by category
  getProductsByCategory(category: string): Observable<ProductResponse[]>{
    return this.http.get<ProductResponse[]>(`${this.apiUrl}/products/category/${category}`);
  }

  // needs to be looked at, it works, it doesn't mean anything it just needs to ensure the call returns data
  /*async getProductsByCategory(category: string): Promise<Observable<ProductResponse[]>>{
    return new Promise(
      (resolve, reject) =>{
        const data = this.http.get<ProductResponse[]>(`${this.apiUrl}/products/${category}/items`);
        if(data === undefined){
          reject("data error");
        }
        resolve(data);
      });
  }*/

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
