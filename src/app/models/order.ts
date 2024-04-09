
export class User {
  userId!: number;
  email!: string;
}

export class Order   {
  orderId!: number;
  orderDate!: Date;
  totalAmount!: number;
  orderStatus!: string;
  user!: User;
}
