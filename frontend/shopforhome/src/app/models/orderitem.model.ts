import Decimal from "decimal.js";

export interface OrderItem {
    itemId?: number;
    productId: number;
    quantity: number;
    price: Decimal;
}
