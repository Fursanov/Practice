import { Component, OnInit } from "@angular/core";
import { CreateOrderItem } from "../models/create-order-item";
import { OrderItemsService } from "../service/order-item.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-create-order-item',
  templateUrl: './create-order-item.component.html',
  styleUrls: ['./create-order-item.component.css']
})
export class CreateOrderItemComponent implements OnInit {
  orderItem: CreateOrderItem = new CreateOrderItem();
  ordersId!: string;
  constructor(private orderItemsService: OrderItemsService,
              private router: Router) {}

  ngOnInit(): void { }

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
    console.log(this.orderItem);
    this.saveOrderItem();
  }
}
