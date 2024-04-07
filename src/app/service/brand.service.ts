import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Brand } from "../models/brand";

@Injectable({
  providedIn: 'root'
})
export class BrandsService {

  private baseUrl = "http://localhost:8080/api/brands"
  constructor(private httpClient: HttpClient) {  }

  getBrandById(id: number) {
    return this.httpClient.get<Brand>(`${this.baseUrl}/get/${id}`);
  }

  getBrandsList(): Observable<Brand[]>{
    return this.httpClient.get<Brand[]>(`${this.baseUrl}/all`);
  }

  createBrand(brand: Brand): Observable<Object>{
    brand.brandId = 0;
    return this.httpClient.post(`${this.baseUrl}/new`, brand);
  }

  updateBrand(id: number, brand: Brand): Observable<Object>{
    brand.brandId = id;
    return this.httpClient.post(`${this.baseUrl}/update`, brand);
  }

  deleteBrand(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }
}
