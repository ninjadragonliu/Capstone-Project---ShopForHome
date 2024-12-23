import { Component } from '@angular/core';
import { currentUser, UserService } from '../services/user.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CartService } from '../services/cart.service';
import { CartItem } from '../models/cartitem.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginSuccess: boolean | null = null;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private userService: UserService,
    private cartService: CartService,
    private router: Router
  ) { }

  onSubmit(form: NgForm) {
    if (form.valid) {
      const username = form.value.username.trim();
      const password = form.value.password.trim();
      this.userService.login(username, password).subscribe({
        next: response => {
          // login user
          this.userService.setLoginStatus(true);
          this.loginSuccess = true;
          this.successMessage = 'Login Success!';
          this.errorMessage = null;
          this.userService.setLoginStatus(true);
          currentUser.userId = response.userId;
          // merge cart on successful login
          const localCart = this.cartService.getLocalCart();
          this.cartService.getCartByUser(response.userId).subscribe(
            backendCart => {
              const mergedCart = this.mergeCarts(localCart.cartItems, backendCart.cartItems);
              this.cartService.syncCartToBackend(response.userId, backendCart.cartId, mergedCart).subscribe(
                () => {
                  this.clearLocalCart();
                }
              );
            }
          );

          // navigate to homepage
          this.router.navigate(['/']);
        },
        error: err => {
          this.loginSuccess = false;
          this.errorMessage = 'Login failed. Please check your username or password.';
          this.successMessage = null;
        }
      });
    }
  }

  mergeCarts(localCart: CartItem[], backendCart: CartItem[]): CartItem[]{
    // add backendcart to merged cart
    const mergedCart: CartItem[] = [...backendCart];

    localCart.forEach(localItem => {
      // find backendcart item that matches product id with localcart item if exists
      const existingItem = mergedCart.find(backendItem => backendItem.productId === localItem.productId)
      if(existingItem){
        // add quantity of localcart item to backendcart item
        existingItem.quantity += localItem.quantity;
      } else{
        // if localcart item's product id is new/doesn't exist, add to merged cart
        mergedCart.push(localItem);
      }
    });
    return mergedCart;
  }

  clearLocalCart(){
    localStorage.removeItem('cart');
  }
}
