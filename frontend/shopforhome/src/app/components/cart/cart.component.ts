import { Component } from '@angular/core';
import { CartItem } from '../../models/cartitem.model';
import { CartService } from '../../services/cart.service';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { Cart } from '../../models/cart.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cart: Cart | null = null;
  totalPrice: number = 0;
  errorMessage: string | null = null;
  userId?: number | null = null;

  constructor(
    private cartService: CartService, 
    private userService: UserService, 
    // might need router to move to order page later
    private router: Router
  ){}
  
  // needs change
  ngOnInit(): void{
    if(this.userService.isLoggedIn()){
      this.userId = this.userService.getUserId();
      if(this.userId){
        this.fetchCart();
      } else{
        this.errorMessage = 'You must be logged in to view your cart';
      }
    }
  }

  fetchCart(): void{
    if(this.userId){
      this.cartService.getCartByUser(this.userId).subscribe({
        next:(response: Cart) => {
          this.cart = response;
        },
        error: (err) => {
          this.errorMessage = 'Error fetching cart data';
        }
      });
    }
  }


}
