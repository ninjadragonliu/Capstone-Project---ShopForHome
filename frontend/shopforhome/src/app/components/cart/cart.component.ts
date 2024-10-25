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
  increment(productAccess: number) {
    this.cartItemsWithDetails[productAccess].quantity++;
    this.updateQuantity(this.cartItemsWithDetails[productAccess]);
  }
  decrement(productAccess: number) {
    if (this.cartItemsWithDetails[productAccess].quantity <= 1) return;
    this.cartItemsWithDetails[productAccess].quantity--;
    this.updateQuantity(this.cartItemsWithDetails[productAccess]);
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
  updateQuantity(cartItem: CartItem): void {
    this.cartService.updateCartItemQuantity(this.userService.getUserId(), cartItem.itemId ?? 0, cartItem.quantity).subscribe(
      (updatedItem) => {
        console.log('Updated cart item:', updatedItem);
      },
      (error) => {
        console.error('Error updating cart item:', error);
      }
    );
  }
}
