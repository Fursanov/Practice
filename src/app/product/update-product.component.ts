import { Component, OnInit } from "@angular/core";
import { Product, Brand } from "../models/product";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductsService } from "../service/product.service";
import { BrandsService } from "../service/brand.service";

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateProductComponent implements OnInit {
  product: Product = new Product();
  id!: number;
  brands: any[] = [];

  constructor(private productsService: ProductsService,
              private brandService: BrandsService,
              private route: ActivatedRoute,
              private router: Router) { this.product.brand = new Brand();
  this.product.stores = [];}

  ngOnInit(): void {
    this.loadBrands();
    this.id = this.route.snapshot.params['id']
    this.productsService.getProductById(this.id).subscribe(
      data => {
        this.product = data;
        console.log(this.product);
      },
      error => console.log(error)
    );
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

  goToProductsList() {
    this.router.navigate(['/products']);
  }

  onSubmit() {
    if (this.product.name && this.product.description && this.product.price &&
      this.product.stockQuantity) {
      this.productsService.updateProduct(this.id, this.product).subscribe(
        data => {
          this.goToProductsList();
        },
        error => console.log(error)
      );
    } else alert("заполните все поля для отправки");
  }
}
