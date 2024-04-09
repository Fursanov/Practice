export class Order{
  orderId!: number;
}

export class Product{
  productId!: number;
  name!: string;
}
export class OrderItem {
  orderItemId!: number;
  quantity!: number;
  order!: Order;
  product!: Product;
}
