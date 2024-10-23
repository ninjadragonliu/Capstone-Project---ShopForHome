import { CartItem } from "./cartitem.model";

export interface Cart {
    cartId: number;
    userId: number;
    cartItems: CartItem[];
}
