import Decimal from "decimal.js";

export interface ProductResponse {
    productId: number;
    name: string;
    description: string;
    price: Decimal;
    category: string;
}
