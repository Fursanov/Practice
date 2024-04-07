// products.component.ts
import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product'
import { ProductsService } from '../service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  products: Product[] = [];

  constructor(private productsService: ProductsService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productsService.getProductsList().subscribe(data => {
      this.products = data;
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
}
