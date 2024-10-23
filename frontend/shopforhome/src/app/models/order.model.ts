import Decimal from 'decimal.js';
import { OrderItem } from './orderitem.model';

export interface Order {
    orderId: number;
    userId: number;
    cartId: number;
    orderItems: OrderItem[];
    total: Decimal;
}
