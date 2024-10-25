import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-product',
  templateUrl: './user-product.component.html',
  styleUrl: './user-product.component.css'
})
export class UserProductComponent implements OnInit {
  products: any[] = [];
  filteredProducts: any[] = [];
  searchTerm: string = '';

  constructor(private productService: ProductService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.productService.getProducts().subscribe((products) => {
      this.products = products;
    });
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params['search'];
      if (this.searchTerm) {
        this.filterProducts();
      }
    });
  }

  filterProducts() {
    this.productService.getProductsByName(this.searchTerm).subscribe((products) => {
      this.filteredProducts = products;
    });
  }
}
