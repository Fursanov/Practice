// productReview.component.ts
import { Component, OnInit } from '@angular/core';
import { ProductReviewService } from '../service/product-review.service';
import { Router } from '@angular/router';
import {ProductReview} from "../models/product-review";

@Component({
  selector: 'app-product-reviews',
  templateUrl: './product-review.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class ProductReviewComponent implements OnInit {
  productReviews: ProductReview[] = [];

  constructor(private productReviewService: ProductReviewService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllProductReview();
  }

  getAllProductReview() {
    this.productReviewService.getProductReviewList().subscribe(data => {
      this.productReviews = data;
    })
  }

  updateProductReview(id: number) {
    this.router.navigate(['update-product-review', id]);
  }

  createProductReview() {
    this.router.navigate(['create-product-review']);
  }

  deleteProductReview(id: number) {
    this.productReviewService.deleteProductReview(id).subscribe(data => {
      console.log(data);
      this.getAllProductReview();
    })
  }
}
