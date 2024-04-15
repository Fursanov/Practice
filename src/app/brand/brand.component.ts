// brands.component.ts
import { Component, OnInit } from '@angular/core';
import { Brand } from '../models/brand'
import { BrandsService } from '../service/brand.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-brands',
  templateUrl: './brand.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class BrandComponent implements OnInit {
  brands: Brand[] = [];
  sortByField!: keyof Brand;
  reverse!: boolean;

  constructor(private brandsService: BrandsService,
              private router: Router) { }

  ngOnInit(): void {
    this.sortByField = 'brandName';
    this.reverse = false;
    this.getAllBrands();
  }

  getAllBrands() {
    this.brandsService.getBrandsList().subscribe(data => {
      this.brands = data;
    })
  }

  updateBrand(id: number) {
    this.router.navigate(['update-brand', id]);
  }

  createBrand() {
    this.router.navigate(['create-brand']);
  }

  deleteBrand(id: number) {
    this.brandsService.deleteBrand(id).subscribe(data => {
      console.log(data);
      this.getAllBrands();
    })
  }

  sortBy(field: keyof Brand) {
    if (this.sortByField === field) {
      this.reverse = !this.reverse;
      this.brands.reverse();
    } else {
      this.reverse = false;
      this.sortByField = field;
      this.brands.sort((a, b) => {
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
