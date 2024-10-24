import { CartItem } from "./cartitem.model";

export interface CartResponse {
    cartId: number;
    userId: number;
    cartItems: CartItem[];
}
