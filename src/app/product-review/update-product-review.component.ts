import { Component, OnInit } from "@angular/core";
import { CreateProductReview } from "../models/create-product-review";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductReviewService } from "../service/product-review.service";

@Component({
  selector: 'app-update-product-review',
  templateUrl: './update-product-review.component.html',
  styleUrls: ['./update-product-review.component.css']
})
export class UpdateProductReviewComponent implements OnInit {
  productReview: CreateProductReview = new CreateProductReview();
  id!: number;

  constructor(private productReviewService: ProductReviewService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.productReviewService.getProductReviewById(this.id).subscribe(
      data => {
        this.productReview = data;
      },
      error => console.log(error)
    );
  }

  goToProductReviewList() {
    this.router.navigate(['/product-reviews']);
  }

  onSubmit() {

    this.productReviewService.updateProductReview(this.id, this.productReview).subscribe(
      data => {
        this.goToProductReviewList();
      },
      error => console.log(error)
    );
  }
}
