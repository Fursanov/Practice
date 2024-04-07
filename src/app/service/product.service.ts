import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Product } from "../models/product";
import { CreateProduct } from "../models/create-product";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private baseUrl = "http://localhost:8080/api/products"
  constructor(private httpClient: HttpClient) {  }

  getProductById(id: number) {
    return this.httpClient.get<CreateProduct>(`${this.baseUrl}/get/${id}`);
  }

  getProductsList(): Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.baseUrl}/all`);
  }

  createProduct(product: CreateProduct): Observable<Object>{
    product.productId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, product);
  }

  updateProduct(id: number, product: CreateProduct): Observable<Object>{
    product.productId = id;
    console.log(product);
    return this.httpClient.post(`${this.baseUrl}/update`, product);

  }

  deleteProduct(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
