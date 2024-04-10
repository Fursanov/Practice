import { Brand } from "./brand";

export class User{
  userId!: number;
  userName!: string;
  email!: string;
}

export class Product{
  productId!: number;
  name!: string;
  brand!: Brand;
}

export class ProductReview {
  productReviewId!: number;
  rating!: number;
  reviewText!: string;
  reviewDate!: Date;
  user!: User;
  product!: Product;
}
