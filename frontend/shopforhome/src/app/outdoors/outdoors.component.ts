import { Component, OnInit } from '@angular/core';
import { currentUser } from '../services/user.service';
import { ProductService } from '../services/product.service';

import { ProductResponse } from '../models/productresponse.model';
import { CartItem } from '../models/cartitem.model';
import { CartService } from '../services/cart.service';
import { ChildActivationStart } from '@angular/router';


@Component({
  selector: 'app-outdoors',
  templateUrl: './outdoors.component.html',
  styleUrl: './outdoors.component.css'
})
export class OutdoorsComponent implements OnInit {

  counter:any = 1;


  increment(){
    this.counter++;
  }
  decrement(){
    if(this.counter <=1) return;
    this.counter--;
  }

  constructor(private productService: ProductService, private cartService:CartService) { }

    products:any | undefined;

    onClick(productId:any){

      const item = this.cartService.addProductToCart(currentUser.userId, productId, this.counter);
      item.subscribe(data=> {
         console.log(data);
      })
    };
    

    async ngOnInit(){
      

      // trying to have this autopopulate before onClick needs work, idk if this works
      // disregard my change to a promise, this needs to be worked out
      await this.productService.getProductsByCategory("outdoors").then((data) => {
         data.subscribe((response: any) => {
              this.products = response;
         });
      });
     
      

      
    }

    

    

}
