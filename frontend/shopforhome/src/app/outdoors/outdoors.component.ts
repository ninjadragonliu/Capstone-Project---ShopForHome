import { Component, OnInit } from '@angular/core';
import { currentUser } from '../services/user.service';
import { ProductService } from '../services/product.service';
import { ProductResponse } from '../models/productresponse.model';

@Component({
  selector: 'app-outdoors',
  templateUrl: './outdoors.component.html',
  styleUrl: './outdoors.component.css'
})
export class OutdoorsComponent implements OnInit {

  constructor(private productService: ProductService) { }

    products:any | undefined;

    onClick(){
      console.log(this.products);
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
