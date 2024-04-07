// brands.component.ts
import { Component, OnInit } from '@angular/core';
import { Brand } from '../models/brand'
import { BrandsService } from '../service/brand.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-brands',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {
  brands: Brand[] = [];

  constructor(private brandsService: BrandsService,
              private router: Router) { }

  ngOnInit(): void {
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
}
