import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Order } from "../models/order";
import { CreateOrder } from "../models/create-order";

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private baseUrl = "http://localhost:8080/api/orders"
  constructor(private httpClient: HttpClient) {  }

  getOrderById(id: number) {
    return this.httpClient.get<Order>(`${this.baseUrl}/get/${id}`);
  }

  getOrdersList(): Observable<Order[]>{
    return this.httpClient.get<Order[]>(`${this.baseUrl}/all`);
  }

  createOrder(order: CreateOrder): Observable<Object>{
    order.orderId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, order);
  }

  updateOrder(id: number, order: Order): Observable<Object>{
    order.orderId = id;
    console.log(order);
    return this.httpClient.post(`${this.baseUrl}/update`, order);

  }

  deleteOrder(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
