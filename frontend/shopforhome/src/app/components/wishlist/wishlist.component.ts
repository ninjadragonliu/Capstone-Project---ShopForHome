import { Component, OnInit } from '@angular/core';
import { Wishlist } from '../../models/wishlist.model';
import { currentUser, UserService } from '../../services/user.service';
import { WishlistService } from '../../services/wishlist.service';
import { ProductService } from '../../services/product.service';
import { UserResponse } from '../../models/userresponse.model';
import { WishlistItem } from '../../models/wishlistitem.model';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrl: './wishlist.component.css'
})
export class WishlistComponent implements OnInit {
  wishlist: Wishlist | null = null;
  wishlistWithDetails: any[] = [];

  constructor(private wishlistService: WishlistService, private productService: ProductService
    , private userService: UserService
  ) { }

  ngOnInit(): void {

    this.wishlistService.getWishlistByUser(this.userService.getUserId()).subscribe((data) => {
      this.wishlist = data;
      console.log('Wishlist:', this.wishlist);
      console.log('Wishlist items:', this.wishlist.wishlistItems);
      
      this.wishlist.wishlistItems.forEach((item: WishlistItem) => {
        this.productService.getProductById(item.productId).subscribe(
          (productDetails) => {
            this.wishlistWithDetails.push({
              ...item,
              name: productDetails.name,
              price: productDetails.price,
              description: productDetails.description
            });
          });

      });

    })
  }
}
