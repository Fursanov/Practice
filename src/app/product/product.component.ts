// products.component.ts
import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product'
import { ProductsService } from '../service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './product.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  sortByField!: keyof Product;
  reverse!: boolean;

  constructor(private productsService: ProductsService,
              private router: Router) { }

  ngOnInit(): void {
    this.sortByField = 'name';
    this.reverse = false;
    this.getAllProducts();
  }

  getAllProducts() {
    this.productsService.getProductsList().subscribe(data => {
      this.products = data;
      console.log(this.products);
    })
  }

  updateProduct(id: number) {
    this.router.navigate(['update-product', id]);
  }

  createProduct() {
    this.router.navigate(['create-product']);
  }

  deleteProduct(id: number) {
    
    this.productsService.deleteProduct(id).subscribe(data => {
      console.log(data);
      this.getAllProducts();
    })
  }

  sortBy(field: keyof Product) {
    if (this.sortByField === field) {
      this.reverse = !this.reverse;
      this.products.reverse();
    } else {
      this.reverse = false;
      this.sortByField = field;
      this.products.sort((a, b) => {
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
