import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ProductReview } from "../models/product-review";
import { CreateProductReview } from "../models/create-product-review";

@Injectable({
  providedIn: 'root'
})
export class ProductReviewService {

  private baseUrl = "http://localhost:8080/api/productReviews"
  constructor(private httpClient: HttpClient) {  }

  getProductReviewById(id: number) {
    return this.httpClient.get<ProductReview>(`${this.baseUrl}/get/${id}`);
  }

  getProductReviewList(): Observable<ProductReview[]>{
    return this.httpClient.get<ProductReview[]>(`${this.baseUrl}/all`);
  }

  createProductReview(productReview: CreateProductReview): Observable<Object>{
    productReview.productReviewId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, productReview);
  }

  updateProductReview(id: number, productReview: ProductReview): Observable<Object>{
    productReview.productReviewId = id;
    console.log(productReview);
    return this.httpClient.post(`${this.baseUrl}/update`, productReview);

  }

  deleteProductReview(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
