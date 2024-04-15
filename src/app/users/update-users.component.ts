import { Component, OnInit } from "@angular/core";
import { User } from "../models/user";
import { ActivatedRoute, Router } from "@angular/router";
import { UsersService } from "../service/users.service";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-users.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateUserComponent implements OnInit {
  user: User = new User();
  id!: number;

  constructor(private usersService: UsersService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.usersService.getUserById(this.id).subscribe(
      data => {
        this.user = data;
      },
      error => console.log(error)
    );
  }

  goToUsersList() {
    this.router.navigate(['/users']);
  }

  onSubmit() {
    if (this.user.userName && this.user.email &&
      this.user.role && this.user.passwordHash) {
      this.usersService.updateUser(this.id, this.user).subscribe(
        data => {
          this.goToUsersList();
        },
        error => console.log(error)
      );
    } else alert("заполните все поля для отправки");
  }
}
