import { Component, OnInit } from "@angular/core";
import { Brand } from "../models/brand";
import { ActivatedRoute, Router } from "@angular/router";
import { BrandsService } from "../service/brand.service";

@Component({
  selector: 'app-update-brand',
  templateUrl: './update-brand.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateBrandComponent implements OnInit {
  brand: Brand = new Brand();
  id!: number;

  constructor(private brandsService: BrandsService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.brandsService.getBrandById(this.id).subscribe(
      data => {
        this.brand = data;
      },
      error => console.log(error)
    );
  }

  goToBrandsList() {
    this.router.navigate(['/brands']);
  }

  onSubmit() {
    this.brandsService.updateBrand(this.id, this.brand).subscribe(
      data => {
        this.goToBrandsList();
      },
      error => console.log(error)
    );
  }
}
