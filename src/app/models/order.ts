
export class User {
  userId!: number;
  email!: string;
  userName!: string;
}
export class Order   {
  orderId!: number;
  orderDate!: Date;
  totalAmount!: number;
  orderStatus!: string;
  user!: User;
}
