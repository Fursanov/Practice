import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Store } from "../models/store";

@Injectable({
  providedIn: 'root'
})
export class StoresService {

  private baseUrl = "http://localhost:8080/api/stores"
  constructor(private httpClient: HttpClient) {  }

  getStoreById(id: number) {
    return this.httpClient.get<Store>(`${this.baseUrl}/get/${id}`);
  }

  getStoresList(): Observable<Store[]>{
    return this.httpClient.get<Store[]>(`${this.baseUrl}/all`);
  }

  createStore(store: Store): Observable<Object>{
    store.storeId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, store);
  }

  updateStore(id: number, store: Store): Observable<Object>{
    store.storeId = id;
    return this.httpClient.post(`${this.baseUrl}/products/update`, store);
  }

  deleteStore(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
