// users.component.ts
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user'
import { UsersService } from '../service/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class UsersComponent implements OnInit {
  users: User[] = [];

  constructor(private usersService: UsersService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {
    this.usersService.getUsersList().subscribe(data => {
      this.users = data;
    })
  }

  updateUser(id: number) {
    this.router.navigate(['update-user', id]);
  }

  createUser() {
    this.router.navigate(['create-user']);
  }

  deleteUser(id: number) {
    this.usersService.deleteUser(id).subscribe(data => {
      console.log(data);
      this.getAllUsers();
    })
  }
}
