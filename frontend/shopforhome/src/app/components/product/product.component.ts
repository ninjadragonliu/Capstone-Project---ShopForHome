import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import Decimal from 'decimal.js';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  products: any[] = [];

  selectAll: any

  selectedProducts: any[] = [];
  selectedProduct: any = null;

  // selectProduct: boolean = false;
  // tempName?: string;
  // tempDescription?: string;
  // tempCategory?: string;
  // tempPrice?: typeof Decimal;
  // tempStock?: number;


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

  // updateSelectedProducts() {
  //   this.selectedProducts = this.getSelectedProducts();
  //   this.selectedProducts.forEach(product => {
  //     this.productService.updateProduct(product.productId, product).subscribe();
  //   });
  // }
  // updateProduct() {
  //   this.selectProduct = true;
  // }
  // cancelEdit() {
  //   this.selectProduct = false;
  // }

  updateProduct() {
    const selectedProducts = this.getSelectedProducts();
    if (selectedProducts.length === 1) {
      this.selectedProduct = { ...selectedProducts[0] }; // Create a copy of the selected product for editing
    } else {
      alert("Please select exactly one product to update.");
    }
  }

  onUpdateProductSubmit() {
    if (this.selectedProduct) {
      this.productService.updateProduct(this.selectedProduct.productId, this.selectedProduct).subscribe(updatedProduct => {
        const index = this.products.findIndex(p => p.productId === updatedProduct.productId);
        this.products[index] = updatedProduct; // Update the local list with the updated product
        this.selectedProduct = null; // Clear the selection
      });
    }
  }

  cancelEdit() {
    this.selectedProduct = null; // Clear the selected product
  }
}
