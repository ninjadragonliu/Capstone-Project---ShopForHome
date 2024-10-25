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
  wishlist!: Wishlist;
  wishlistItems: WishlistItem[] = [];
  wishlistWithDetails: any[] = [];

  constructor(private wishlistService: WishlistService, private productService: ProductService
    , private userService: UserService
  ) { }

  ngOnInit(): void {
    this.wishlistService.getWishlistByUser(currentUser.userId).subscribe(
      (data: Wishlist) => {
        this.wishlist = data;
        this.wishlistItems = data.wishlistItems; // Assigning the array of wishlist items
        console.log(currentUser.userId);
        console.log("Wishlist:", data);
        console.log(data.wishlistItems);
        // Now fetch product details for each item
        this.wishlistItems.forEach((item: WishlistItem) => {
          this.productService.getProductById(item.productId).subscribe(
            (productDetails) => {
              this.wishlistWithDetails.push({
                ...item,
                name: productDetails.name,
                price: productDetails.price,
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
