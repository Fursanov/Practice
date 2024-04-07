import { Component, OnInit } from "@angular/core";
import { CreateProduct } from "../models/create-product";
import { ActivatedRoute, Router } from "@angular/router";
import { ProductsService } from "../service/product.service";

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  product: CreateProduct = new CreateProduct();
  id!: number;

  constructor(private productsService: ProductsService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.productsService.getProductById(this.id).subscribe(
      data => {
        this.product = data;
      },
      error => console.log(error)
    );
  }

  goToProductsList() {
    this.router.navigate(['/products']);
  }

  onSubmit() {

    this.productsService.updateProduct(this.id, this.product).subscribe(
      data => {
        this.goToProductsList();
      },
      error => console.log(error)
    );
  }
}
