import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { OrderItem } from "../models/order-item";
import { CreateOrderItem } from "../models/create-order-item";

@Injectable({
  providedIn: 'root'
})
export class OrderItemsService {

  private baseUrl = "http://localhost:8080/api/orderItems"
  constructor(private httpClient: HttpClient) {  }

  getOrderItemById(id: number) {
    return this.httpClient.get<OrderItem>(`${this.baseUrl}/get/${id}`);
  }

  getOrderItemsList(): Observable<OrderItem[]>{
    return this.httpClient.get<OrderItem[]>(`${this.baseUrl}/all`);
  }

  createOrderItem(orderItem: CreateOrderItem): Observable<Object>{
    orderItem.orderItemId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, orderItem);
  }

  updateOrderItem(id: number, orderItem: OrderItem): Observable<Object>{
    orderItem.orderItemId = id;
    console.log(orderItem);
    return this.httpClient.post(`${this.baseUrl}/update`, orderItem);

  }

  deleteOrderItem(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
