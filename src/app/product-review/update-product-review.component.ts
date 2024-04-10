import { Component, OnInit } from "@angular/core";
import {Product, ProductReview, User } from "../models/product-review";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductReviewService } from "../service/product-review.service";
import { UsersService } from "../service/users.service";
import { ProductsService } from "../service/product.service";

@Component({
  selector: 'app-update-product-review',
  templateUrl: './update-product-review.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateProductReviewComponent implements OnInit {
  productReview: ProductReview = new ProductReview();
  id!: number;
  users: any[] = [];
  products: any[] =[];

  constructor(private productReviewService: ProductReviewService,
              private userService: UsersService,
              private productService: ProductsService,
              private route: ActivatedRoute,
              private router: Router) {
    this.productReview.product = new Product();
    this.productReview.user = new User();
  }

  ngOnInit(): void {
    this.loadUsers();
    this.loadProducts();
    this.id = this.route.snapshot.params['id']
    this.productReviewService.getProductReviewById(this.id).subscribe(
      data => {
        this.productReview = data;
      },
      error => console.log(error)
    );
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

  goToProductReviewList() {
    this.router.navigate(['/product-reviews']);
  }

  onSubmit() {
    if (this.productReview.rating && this.productReview.reviewText) {
      if(this.productReview.rating > 0 && this.productReview.rating < 11) {
        this.productReviewService.updateProductReview(this.id, this.productReview).subscribe(
          data => {
            this.goToProductReviewList();
          },
          error => console.log(error)
        );
      } else alert("некорректное значение оценки");
    } else alert("заполните все поля для отправки");
  }
}
