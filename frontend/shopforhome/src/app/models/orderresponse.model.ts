import Decimal from "decimal.js";
import { OrderItem } from "./orderitem.model";

export interface OrderResponse {
    orderId: number;
    userId: number;
    cartId: number;
    orderItems: OrderItem[];
    total: Decimal;
}
