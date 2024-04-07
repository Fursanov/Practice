import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private baseUrl = "http://localhost:8080/api/users"
  constructor(private httpClient: HttpClient) {  }

  getUserById(id: number) {
    return this.httpClient.get<User>(`${this.baseUrl}/get/${id}`);
  }

  getUsersList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseUrl}/all`);
  }

  createUser(user: User): Observable<Object>{
    user.userId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, user);
  }

  updateUser(id: number, user: User): Observable<Object>{
    user.userId = id;
    return this.httpClient.post(`${this.baseUrl}/update`, user);
  }

  deleteUser(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
