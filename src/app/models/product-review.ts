export class User{
  userId!: number;
}

export class Product{
  productId!: number;
}

export class ProductReview {
  productReviewId!: number;
  rating!: number;
  reviewText!: string;
  reviewDate!: Date;
  user!: User;
  product!: Product;
}
