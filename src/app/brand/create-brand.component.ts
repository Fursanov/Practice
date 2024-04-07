import {Component, OnInit} from "@angular/core";
import {Brand} from "../models/brand";
import {BrandsService} from "../service/brand.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-brand',
  templateUrl: './create-brand.component.html',
  styleUrls: ['./create-brand.component.css']
})
export class CreateBrandComponent implements OnInit {
  brand: Brand = new Brand();

  constructor(private brandsService: BrandsService,
              private router: Router) { }

  ngOnInit(): void { }

  saveBrand() {
    this.brandsService.createBrand(this.brand).subscribe(
      data => {
        console.log(data);
        this.goToBrandsList();
      },
      error => console.log(error)
    );
  }

  goToBrandsList() {
    this.router.navigate(['/brands']);
  }

  onSubmit() {
    console.log(this.brand);
    this.saveBrand();
  }
}
