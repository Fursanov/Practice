import { Component, OnInit } from "@angular/core";
import { Store } from "../models/store";
import { ActivatedRoute, Router } from "@angular/router";
import { StoresService } from "../service/store.service";

@Component({
  selector: 'app-update-store',
  templateUrl: './update-store.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class UpdateStoreComponent implements OnInit {
  store: Store = new Store();
  id!: number;

  constructor(private storesService: StoresService,
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.storesService.getStoreById(this.id).subscribe(
      data => {
        this.store = data;
      },
      error => console.log(error)
    );
  }

  goToStoresList() {
    this.router.navigate(['/stores']);
  }

  onSubmit() {
    if (this.store.storeName && this.store.location) {
      this.storesService.updateStore(this.id, this.store).subscribe(
        data => {
          this.goToStoresList();
        },
        error => console.log(error)
      );
    } else alert("заполните все поля для отправки");
  }
}
