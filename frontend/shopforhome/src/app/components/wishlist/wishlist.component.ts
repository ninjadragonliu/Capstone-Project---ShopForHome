import { Component, OnInit } from '@angular/core';
import { Wishlist } from '../../models/wishlist.model';
import { UserService } from '../../services/user.service';
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
  wishlist!: Wishlist;
  wishlistWithDetails: any[] = [];

  constructor(private wishlistService: WishlistService, private productService: ProductService
    , private userService: UserService
  ) { }

  ngOnInit(): void {
    const userId = this.userService.getUserId();
    this.wishlistService.getWishlistByUser(userId).subscribe(
      (data: Wishlist) => {
        this.wishlist = data;

        // Now fetch product details for each item
        this.wishlist.wishlistItems.forEach((item: WishlistItem) => {
          this.productService.getProductById(item.productId).subscribe(
            (productDetails) => {
              this.wishlistWithDetails.push({
                ...item,
                name: productDetails.name,
                description: productDetails.description
              });
            },
            (error) => {
              console.error('Error fetching product details:', error);
            }
          );
        });
      },
      (error) => {
        console.error('Error fetching wishlist:', error);
      }
    );
    
  }
}
