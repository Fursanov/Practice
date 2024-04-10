import { Brand } from "./brand";
import { User } from "./user";

export class Order{
  orderId!: number;
  orderDate!: Date;
  user!: User;
}

export class Product{
  productId!: number;
  name!: string;
  brand!: Brand;
}
export class OrderItem {
  orderItemId!: number;
  quantity!: number;
  order!: Order;
  product!: Product;
}
