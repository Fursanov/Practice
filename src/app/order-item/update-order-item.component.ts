import { Component, OnInit } from "@angular/core";
import {Order, OrderItem, Product } from "../models/order-item";
import { ActivatedRoute, Router } from "@angular/router";
import { OrderItemsService } from "../service/order-item.service";
import { OrdersService } from "../service/order.service";
import { ProductsService } from "../service/product.service";

@Component({
  selector: 'app-update-order-item',
  templateUrl: './update-order-item.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateOrderItemComponent implements OnInit {
  orderItem: OrderItem = new OrderItem();
  id!: number;
  orders: any[] = [];
  products: any[] =[];

  constructor(private orderItemsService: OrderItemsService,
              private orderService: OrdersService,
              private productService: ProductsService,
              private route: ActivatedRoute,
              private router: Router) {
    this.orderItem.order = new Order();
    this.orderItem.product = new Product();}

  ngOnInit(): void {
    this.loadOrders();
    this.loadProducts();
    this.id = this.route.snapshot.params['id']
    this.orderItemsService.getOrderItemById(this.id).subscribe(
      data => {
        this.orderItem = data;
      },
      error => console.log(error)
    );
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

  goToOrderItemsList() {
    this.router.navigate(['/order-items']);
  }

  onSubmit() {

    this.orderItemsService.updateOrderItem(this.id, this.orderItem).subscribe(
      data => {
        this.goToOrderItemsList();
      },
      error => console.log(error)
    );
  }
}
