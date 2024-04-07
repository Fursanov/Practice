import {Component, OnInit} from "@angular/core";
import {CreateOrder} from "../models/create-order";
import {OrdersService} from "../service/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {
  order: CreateOrder = new CreateOrder();
  constructor(private ordersService: OrdersService,
              private router: Router) { }

  ngOnInit(): void { }

  saveOrder() {
    this.ordersService.createOrder(this.order).subscribe(
      data => {
        console.log(data);
        this.goToOrdersList();
      },
      error => console.log(error)
    );
  }

  goToOrdersList() {
    this.router.navigate(['/orders']);
  }

  onSubmit() {
    console.log(this.order);
    this.saveOrder();
  }
}
