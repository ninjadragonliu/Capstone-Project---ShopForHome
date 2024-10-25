import { Component, OnInit } from '@angular/core';
import { currentUser } from '../services/user.service';
import { ProductService } from '../services/product.service';

import { ProductResponse } from '../models/productresponse.model';
import { CartItem } from '../models/cartitem.model';
import { CartService } from '../services/cart.service';
import { ChildActivationStart } from '@angular/router';
import { WishlistService } from '../services/wishlist.service';


@Component({
  selector: 'app-outdoors',
  templateUrl: './outdoors.component.html',
  styleUrl: './outdoors.component.css'
})



export class OutdoorsComponent implements OnInit {

  counter:any = 1;

  products:any | undefined;
 

  constructor(private productService: ProductService, private cartService:CartService, private wishlistService:WishlistService) { }

    


    increment(productAccess:number){
      this.products[productAccess].counter++;
    }
    decrement(productAccess:number){
      if(this.products[productAccess].counter <=1) return;
      this.products[productAccess].counter--;
    }

    onClickCart(productId:any, productAccess:number){

      const item = this.cartService.addProductToCart(currentUser.userId, productId, this.products[productAccess].counter);
      item.subscribe( data => {
        console.log(data);
      })
    };


    onClickWishlist(productId:any){
      console.log(productId);
      const item = this.wishlistService.addProductToWishlist(currentUser.userId, productId);
      item.subscribe(data => {
         console.log(data);
      });
    } 

    

    async ngOnInit(){
      

      // trying to have this autopopulate before onClick needs work, idk if this works
      // disregard my change to a promise, this needs to be worked out
      await this.productService.getProductsByCategory("outdoors").then((products) => {
         products.subscribe(productList => {


           productList.forEach((element:any) => {
           
            element["counter"] = 1;

            
 
          });

          this.products = productList;

       });
      });
     
      

      
    }

    

    

}
