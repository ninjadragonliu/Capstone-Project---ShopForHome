import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  products: any[] = [];

  selectAll: any

  selectedProducts: any[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe((products) => {
      this.products = products.map(product => ({ ...product, selected: false }));
    })
  }

  toggleSelectAll() {
    this.products.forEach(product => (product.selected = this.selectAll));
  }

  updateSelectAllState() {
    this.selectAll = this.products.every(product => product.selected);
  }

  getSelectedProducts() {
    return this.products.filter(product => product.selected);
  }

  deleteSelectedProducts() {
    this.selectedProducts = this.getSelectedProducts();
    this.selectedProducts.forEach(product => {
      this.productService.deleteProduct(product.productId).subscribe();
    });
    this.products = this.products.filter(product => !product.selected);
  }

  updateSelectedProducts() {
    const selectedProducts = this.getSelectedProducts();
    selectedProducts.forEach(product => {
      this.productService.updateProduct(product.productId, product).subscribe();
    });
  }
}
