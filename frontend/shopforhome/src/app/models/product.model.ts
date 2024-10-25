import Decimal from 'decimal.js';

export interface Product {
    productId: number;
    name: string;
    description: string;
    price: typeof Decimal;
    category: string;
}
