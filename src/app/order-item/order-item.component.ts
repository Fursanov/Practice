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
  sortByField!: keyof OrderItem;
  reverse!: boolean;

  constructor(private orderItemsService: OrderItemsService,
              private router: Router) { }

  ngOnInit(): void {
    this.sortByField = 'quantity';
    this.reverse = false;
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

  sortBy(field: keyof OrderItem) {
    if (this.sortByField === field) {
      this.reverse = !this.reverse;
      this.orderItems.reverse();
    } else {
      this.reverse = false;
      this.sortByField = field;
      this.orderItems.sort((a, b) => {
        if (a[field] < b[field]) {
          return -1;
        } else if (a[field] > b[field]) {
          return 1;
        } else {
          return 0;
        }
      });
    }
  }
}
