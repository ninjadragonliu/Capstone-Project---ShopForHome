import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';

import { ProductResponse } from '../models/productresponse.model';
import { CartItem } from '../models/cartitem.model';
import { CartService } from '../services/cart.service';
import { ChildActivationStart } from '@angular/router';
import { WishlistService } from '../services/wishlist.service';
import { currentUser } from '../services/user.service';

const buttonAddMessage = "Add to ";
const buttonAddedMessage = "Added to ";

@Component({
  selector: 'app-kitchen',
  templateUrl: './kitchen.component.html',
  styleUrl: './kitchen.component.css'
})



export class KitchenComponent implements OnInit {
  counter: any = 1;
  products: any[] = [];
  category: string = 'kitchen';

  constructor(private productService: ProductService, private cartService: CartService, private wishlistService: WishlistService) { }

  increment(productAccess: number) {
    this.products[productAccess].counter++;
  }
  decrement(productAccess: number) {
    if (this.products[productAccess].counter <= 1) return;
    this.products[productAccess].counter--;
  }

  onClickCart(productId: any, productAccess: number) {
    console.log(currentUser.userId);
    const item = this.cartService.addProductToCart(currentUser.userId, productId, this.products[productAccess].counter);
    item.subscribe(data => {
      console.log(data);
    })
  };

  onClickWishlist(productId: any) {
    console.log(productId);
    const item = this.wishlistService.addProductToWishlist(currentUser.userId, productId);
    item.subscribe(data => {
      console.log(data);
    });
  }

  ngOnInit() {
    this.fetchProductsByCategory(this.category);
  }

  fetchProductsByCategory(category: string) {
    this.productService.getProductsByCategory(category).subscribe(productList => {
      this.products = productList.map(product => {
        return { ...product, counter: 1 };
      });
    });
  }

  onCategoryChange(newCategory: string) {
    this.category = newCategory;
    this.fetchProductsByCategory(this.category);
  }
}
