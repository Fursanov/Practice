export class User{
  userId!: number;
  userName!: string;
}

export class Product{
  productId!: number;
  name!: string;
}

export class ProductReview {
  productReviewId!: number;
  rating!: number;
  reviewText!: string;
  reviewDate!: Date;
  user!: User;
  product!: Product;
}
