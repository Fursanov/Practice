import { Component, OnInit } from "@angular/core";
import { CreateOrderItem } from "../models/create-order-item";
import { OrderItemsService } from "../service/order-item.service";
import { Router } from "@angular/router";
import { OrdersService } from "../service/order.service";
import { ProductsService } from "../service/product.service";

@Component({
  selector: 'app-create-order-item',
  templateUrl: './create-order-item.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class CreateOrderItemComponent implements OnInit {
  orderItem: CreateOrderItem = new CreateOrderItem();
  ordersId!: string;
  orders: any[] = [];
  products: any[] =[];

  constructor(private orderItemsService: OrderItemsService,
              private orderService: OrdersService,
              private productService: ProductsService,
              private router: Router) {}

  ngOnInit(): void {
    this.loadOrders();
    this.loadProducts();
  }

  loadOrders() {
    this.orderService.getOrdersList().subscribe(
      data => {
        this.orders = data;
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

  saveOrderItem() {
    this.orderItemsService.createOrderItem(this.orderItem).subscribe(
      data => {
        console.log(data);
        this.goToOrderItemsList();
      },
      error => console.log(error)
    );
  }

  goToOrderItemsList() {
    this.router.navigate(['/order-items']);
  }

  onSubmit() {
    if (this.orderItem.quantity && this.orderItem.orderId && this.orderItem.productId) {
      console.log(this.orderItem);
      this.saveOrderItem();
    } else alert("заполните все поля для отправки");
  }

  getFormatTime(date: any) {
    const [datePart, timePart] = date.split('T');
    const [year, month, day] = datePart.split('-');
    const [time, timeZone] = timePart.split('.');
    return `${day}.${month}.${year} ${time}`;
  }
}
