// stores.component.ts
import { Component, OnInit } from '@angular/core';
import {Product, Store } from '../models/store'
import { StoresService } from '../service/store.service';
import {ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from '../service/product.service';

@Component({
  selector: 'app-product-store',
  templateUrl: './product-store.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class ProductStoreComponent implements OnInit {
  addProducts: any[] = [];
  id!: number;
  store: Store = new Store();
  newProduct!: Product;

  constructor(private storesService: StoresService,
              private productsService: ProductsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.storesService.getStoreById(this.id).subscribe(
      data => {
        this.store = data;
      }
    )
    this.productsService.getProductsList().subscribe(
      data => {
        this.addProducts = data;
      },
    );
  }

  addProduct(): void {
    this.store.products.push(this.newProduct);
    console.log(this.store);
    this.storesService.updateStore(this.id, this.store).subscribe(
      data => {
      },
      error => console.log(error)
    );
  }

  deleteProduct(product: Product) {
    this.store.products.splice(this.store.products.indexOf(product), 1);
    this.storesService.updateStore(this.id, this.store).subscribe(
      data => {
      },
      error => console.log(error)
    );
  }

  back() {
    this.router.navigate(['/stores']);
  }
}
