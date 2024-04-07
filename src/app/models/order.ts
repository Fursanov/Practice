
export class User {
  userId!: number;
}

export class Order   {
  orderId!: number;
  orderDate!: Date;
  totalAmount!: number;
  orderStatus!: string;
  user!: User;
}
