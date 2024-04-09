import {Component, OnInit} from "@angular/core";
import {User} from "../models/user";
import {UsersService} from "../service/users.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-users.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class CreateUsersComponent implements OnInit {
  user: User = new User();

  constructor(private usersService: UsersService,
              private router: Router) { }

  ngOnInit(): void { }

  saveUser() {
    this.usersService.createUser(this.user).subscribe(
      data => {
        console.log(data);
        this.goToUsersList();
      },
      error => console.log(error)
    );
  }

  goToUsersList() {
    this.router.navigate(['/users']);
  }

  onSubmit() {
    console.log(this.user);
    this.saveUser();
  }
}
