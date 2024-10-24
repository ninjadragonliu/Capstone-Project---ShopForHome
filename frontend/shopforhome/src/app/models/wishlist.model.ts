import { WishlistItem } from "./wishlistitem.model";

export interface Wishlist {
    wishlistId?: number;
    userId: number;
    wishlistItems: WishlistItem[];
}
