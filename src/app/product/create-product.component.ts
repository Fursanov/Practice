import { Component, OnInit } from "@angular/core";
import { CreateProduct } from "../models/create-product";
import { ProductsService } from "../service/product.service";
import { Router } from "@angular/router";
import { BrandsService } from "../service/brand.service";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class CreateProductComponent implements OnInit {
  product: CreateProduct = new CreateProduct();
  brands: any[] = [];
  constructor(private productsService: ProductsService,
              private brandService: BrandsService,
              private router: Router) { this.product.storesId = [] }

  ngOnInit(): void {
    this.loadBrands();
  }

  loadBrands() {
    this.brandService.getBrandsList().subscribe(
      data => {
        this.brands = data;
      },
      error => {
        console.log(error);
      }
    );
  }

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
