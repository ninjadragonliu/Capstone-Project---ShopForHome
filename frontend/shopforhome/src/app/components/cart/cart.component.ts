import { Component, OnInit } from '@angular/core';
import { Cart } from '../../models/cart.model';
import { CartService } from '../../services/cart.service';
import { ProductService } from '../../services/product.service';
import { CartItem } from '../../models/cartitem.model';
import { UserInterfaceHeaderComponent } from '../../user-interface-header/user-interface-header.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit{
  cart!: Cart;
  cartItemsWithDetails: any[] = []; 

  constructor(private cartService: CartService, private productService: ProductService, ) {
  }

  ngOnInit(): void {
    
    this.cartService.getCartByUser(1).subscribe((data) => {
      this.cart = data;

      
      this.cart.cartItems.forEach((item: CartItem) => {
        this.productService.getProductById(item.productId).subscribe(
          (productDetails) => {
            // Combine cart item with its product details and store in cartItemsWithDetails
            this.cartItemsWithDetails.push({
              ...item,
              name: productDetails.name,  // Assuming productDetails has 'name' property
              price: productDetails.price       // Assuming productDetails has 'price' property
            });
          }
        );
      });
      
    });

  }
}
