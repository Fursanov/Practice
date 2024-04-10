import { Component, OnInit } from "@angular/core";
import { CreateProductReview } from "../models/create-product-review";
import { ProductReviewService } from "../service/product-review.service";
import { Router } from "@angular/router";
import { UsersService } from "../service/users.service";
import { ProductsService } from "../service/product.service";

@Component({
  selector: 'app-create-product-review',
  templateUrl: './create-product-review.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class CreateProductReviewComponent implements OnInit {
  productReview: CreateProductReview = new CreateProductReview();
  ordersId!: string;
  users: any[] = [];
  products: any[] =[];

  constructor(private productReviewService: ProductReviewService,
              private userService: UsersService,
              private productService: ProductsService,
              private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
    this.loadProducts();
  }

  loadUsers() {
    this.userService.getUsersList().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  loadProducts() {
    this.productService.getProductsList().subscribe(
      data => {
        this.products = data;
      },
      error => {
        console.log(error);
      }
    );
  }

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
    if (this.productReview.rating && this.productReview.reviewText &&
      this.productReview.userId && this.productReview.productId) {
      if(this.productReview.rating > 0 && this.productReview.rating < 11) {
        console.log(this.productReview);
        this.saveProductreview();
      } else alert("некорректное значение оценки");
    } else alert("заполните все поля для отправки");
  }
}
