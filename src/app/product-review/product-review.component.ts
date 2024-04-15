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
  sortByField!: keyof ProductReview;
  reverse!: boolean;

  constructor(private productReviewService: ProductReviewService,
              private router: Router) { }

  ngOnInit(): void {
    this.sortByField = 'rating';
    this.reverse = false;
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

  getFormatTime(date: any) {
    const [datePart, timePart] = date.split('T');
    const [year, month, day] = datePart.split('-');
    const [time, timeZone] = timePart.split('.');
    return `${day}.${month}.${year} ${time}`;
  }

  sortBy(field: keyof ProductReview) {
    if (this.sortByField === field) {
      this.reverse = !this.reverse;
      this.productReviews.reverse();
    } else {
      this.reverse = false;
      this.sortByField = field;
      this.productReviews.sort((a, b) => {
        if (a[field] < b[field]) {
          return -1;
        } else if (a[field] > b[field]) {
          return 1;
        } else {
          return 0;
        }
      });
    }
  }
}
