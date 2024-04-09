// orderItems.component.ts
import { Component, OnInit } from '@angular/core';
import { OrderItem } from '../models/order-item'
import { OrderItemsService } from '../service/order-item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-item.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class OrderItemComponent implements OnInit {
  orderItems: OrderItem[] = [];

  constructor(private orderItemsService: OrderItemsService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllOrderItems();
  }

  getAllOrderItems() {
    this.orderItemsService.getOrderItemsList().subscribe(data => {
      this.orderItems = data;
    })
  }

  updateOrderItem(id: number) {
    this.router.navigate(['update-order-item', id]);
  }

  createOrderItem() {
    this.router.navigate(['create-order-item']);
  }

  deleteOrderItem(id: number) {
    this.orderItemsService.deleteOrderItem(id).subscribe(data => {
      console.log(data);
      this.getAllOrderItems();
    })
  }
}
