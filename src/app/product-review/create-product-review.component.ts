import { Component, OnInit } from "@angular/core";
import { CreateProductReview } from "../models/create-product-review";
import { ProductReviewService } from "../service/product-review.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-create-product-review',
  templateUrl: './create-product-review.component.html',
  styleUrls: ['./create-product-review.component.css']
})
export class CreateProductReviewComponent implements OnInit {
  productReview: CreateProductReview = new CreateProductReview();
  ordersId!: string;
  constructor(private productReviewService: ProductReviewService,
              private router: Router) {}

  ngOnInit(): void { }

  saveProductreview() {
    this.productReviewService.createProductReview(this.productReview).subscribe(
      data => {
        console.log(data);
        this.goToProductreviewsList();
      },
      error => console.log(error)
    );
  }

  goToProductreviewsList() {
    this.router.navigate(['/product-reviews']);
  }

  onSubmit() {
    console.log(this.productReview);
    this.saveProductreview();
  }
}
