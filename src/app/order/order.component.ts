// orders.component.ts
import { Component, OnInit } from '@angular/core';
import { Order } from '../models/order'
import { OrdersService } from '../service/order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders: Order[] = [];

  constructor(private ordersService: OrdersService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders() {
    this.ordersService.getOrdersList().subscribe(data => {
      this.orders = data;
    })
  }

  updateOrder(id: number) {
    this.router.navigate(['update-order', id]);
  }

  createOrder() {
    this.router.navigate(['create-order']);
  }

  deleteOrder(id: number) {
    this.ordersService.deleteOrder(id).subscribe(data => {
      console.log(data);
      this.getAllOrders();
    })
  }
}
