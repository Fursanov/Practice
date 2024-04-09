// stores.component.ts
import { Component, OnInit } from '@angular/core';
import { Store } from '../models/store'
import { StoresService } from '../service/store.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stores',
  templateUrl: './store.component.html',
  styleUrls: ['../styles/list.component.css']
})
export class StoreComponent implements OnInit {
  stores: Store[] = [];

  constructor(private storesService: StoresService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllStores();
  }

  getAllStores() {
    this.storesService.getStoresList().subscribe(data => {
      this.stores = data;
    })
  }

  updateStore(id: number) {
    this.router.navigate(['update-store', id]);
  }

  showProducts(id: number){
    this.router.navigate(['product-store', id]);
  }

  createStore() {
    this.router.navigate(['create-store']);
  }

  deleteStore(id: number) {
    this.storesService.deleteStore(id).subscribe(data => {
      console.log(data);
      this.getAllStores();
    })
  }
}
