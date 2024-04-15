import {Component, OnInit} from "@angular/core";
import {CreateOrder} from "../models/create-order";
import {OrdersService} from "../service/order.service";
import {UsersService} from "../service/users.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class CreateOrderComponent implements OnInit {
  order: CreateOrder = new CreateOrder();
  users: any[] = [];

  constructor(private ordersService: OrdersService,
              private userService: UsersService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsersList().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.log(error);
      }
    );
  }

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
    if (this.order.userId) {
      console.log(this.order);
      this.saveOrder();
    } else alert("заполните все поля для отправки");
  }
}
