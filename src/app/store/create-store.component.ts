import {Component, OnInit} from "@angular/core";
import {Store} from "../models/store";
import {StoresService} from "../service/store.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-store',
  templateUrl: './create-store.component.html',
  styleUrls: ['../styles/edit.component.css']
})
export class CreateStoreComponent implements OnInit {
  store: Store = new Store();

  constructor(private storesService: StoresService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  saveStore() {
    this.storesService.createStore(this.store).subscribe(
      data => {
        console.log(data);
        this.goToStoresList();
      },
      error => console.log(error)
    );
  }

  goToStoresList() {
    this.router.navigate(['/stores']);
  }

  onSubmit() {
    if (this.store.storeName && this.store.location) {
      console.log(this.store);
      this.saveStore();
    } else alert("заполните все поля для отправки");
  }
}
