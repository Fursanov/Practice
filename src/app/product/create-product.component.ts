import { Component, OnInit } from "@angular/core";
import { CreateProduct } from "../models/create-product";
import { ProductsService } from "../service/product.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  product: CreateProduct = new CreateProduct();
  constructor(private productsService: ProductsService,
              private router: Router) { }

  ngOnInit(): void { }

  saveProduct() {
    this.productsService.createProduct(this.product).subscribe(
      data => {
        console.log(data);
        this.goToProductsList();
      },
      error => console.log(error)
    );
  }

  goToProductsList() {
    this.router.navigate(['/products']);
  }

  onSubmit() {
    console.log(this.product);
    this.saveProduct();
  }
}
