import { Component } from '@angular/core';

@Component({
  selector: 'app-product-dropdown',
  templateUrl: './product-dropdown.component.html',
  styleUrl: './product-dropdown.component.css'
})
export class ProductDropdownComponent {
  products = ['Laptop', 'Mobile', 'Television'];
}
