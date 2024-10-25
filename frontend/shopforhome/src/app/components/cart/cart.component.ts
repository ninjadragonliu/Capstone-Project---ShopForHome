import { Component } from '@angular/core';
import { CartItem } from '../../models/cartitem.model';
import { CartService } from '../../services/cart.service';
import { Router } from '@angular/router';
import { currentUser, UserService } from '../../services/user.service';
import { Cart } from '../../models/cart.model';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cart!: Cart;
  cartItemsWithDetails: any[] = []; 

  constructor(private cartService: CartService, private productService: ProductService, private userService: UserService) {
  }

  ngOnInit(): void {
    
    this.cartService.getCartByUser(this.userService.getUserId()).subscribe((data) => {
      this.cart = data;
      console.log('Cart:', this.cart);
      console.log('Cart items:', this.cart.cartItems);
      
      this.cart.cartItems.forEach((item: CartItem) => {
        this.productService.getProductById(item.productId).subscribe(
          (productDetails) => {
            // Combine cart item with its product details and store in cartItemsWithDetails
            this.cartItemsWithDetails.push({
              ...item,
              name: productDetails.name, 
              price: productDetails.price,
              description: productDetails.description
          }
        );
      });
      
    });

  })
}
}
