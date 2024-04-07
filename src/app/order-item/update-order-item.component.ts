import { Component, OnInit } from "@angular/core";
import { CreateOrderItem } from "../models/create-order-item";
import { ActivatedRoute, Router } from "@angular/router";
import { OrderItemsService } from "../service/order-item.service";

@Component({
  selector: 'app-update-order-item',
  templateUrl: './update-order-item.component.html',
  styleUrls: ['./update-order-item.component.css']
})
export class UpdateOrderItemComponent implements OnInit {
  orderItem: CreateOrderItem = new CreateOrderItem();
  id!: number;

  constructor(private orderItemsService: OrderItemsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.orderItemsService.getOrderItemById(this.id).subscribe(
      data => {
        this.orderItem = data;
      },
      error => console.log(error)
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
