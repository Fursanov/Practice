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
  sortByField!: keyof User;
  reverse!: boolean;

  constructor(private usersService: UsersService,
              private router: Router) { }

  ngOnInit(): void {
    this.sortByField = 'userName';
    this.reverse = false;
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

  sortBy(field: keyof User) {
    if (this.sortByField === field) {
      this.reverse = !this.reverse;
      this.users.reverse();
    } else {
      this.reverse = false;
      this.sortByField = field;
      this.users.sort((a, b) => {
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
