import { Component, OnInit } from "@angular/core";
import { Order, User } from "../models/order";
import { ActivatedRoute, Router } from "@angular/router";
import { OrdersService } from "../service/order.service";
import { UsersService } from "../service/users.service";

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateOrderComponent implements OnInit {
  order: Order = new Order();
  id!: number;
  users: any[] = [];

  constructor(private ordersService: OrdersService,
              private userService: UsersService,
              private route: ActivatedRoute,
              private router: Router) { this.order.user = new User();}

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

  ngOnInit(): void {
    this.loadUsers();
    this.id = this.route.snapshot.params['id']
    this.ordersService.getOrderById(this.id).subscribe(
      data => {
        this.order = data;
      },
      error => console.log(error)
    );
  }

  goToOrdersList() {
    this.router.navigate(['/orders']);
  }

  onSubmit() {
    this.ordersService.updateOrder(this.id, this.order).subscribe(
      data => {
        this.goToOrdersList();
      },
      error => console.log(error)
    );
  }
}
