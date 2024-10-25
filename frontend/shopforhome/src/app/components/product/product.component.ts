import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  products: any[] = [];
  showProducts = false

  constructor(private productService: ProductService) { }

  showProductManagement() {
    this.showProducts = true;
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
    })
  }
}
