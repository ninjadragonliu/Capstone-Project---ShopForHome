import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../services/user.service';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-administrator-interface',
  templateUrl: './administrator-interface.component.html',
  styleUrl: './administrator-interface.component.css'
})
export class AdministratorInterfaceComponent {
    products: any[] = [];

    showProducts = false;

    isLoggedIn: boolean = false;
    userRole?: string | null = null;
    constructor(private userService: UserService, private productService: ProductService) {}
    showProductManagement() {
        this.showProducts = true;
        this.productService.getProducts().subscribe((products) => {
          this.products = products;
        })
    }
    logout(){
      this.userService.logout();
      this.isLoggedIn = false;
      this.userRole = null;
  
    }
}
