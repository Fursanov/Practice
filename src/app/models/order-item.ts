export class Order{
  orderId!: number;
}

export class Product{
  productId!: number;
}
export class OrderItem {
  orderItemId!: number;
  quantity!: number;
  order!: Order;
  product!: Product;
}
