import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {UsersComponent} from "./users/users.component";
import {UpdateUserComponent} from "./users/update-users.component";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {CreateUsersComponent} from "./users/create-users.component";
import { BrandComponent } from './brand/brand.component';
import { CreateBrandComponent } from './brand/create-brand.component';
import { UpdateBrandComponent } from './brand/update-brand.component';
import { StoreComponent } from './store/store.component';
import { UpdateStoreComponent } from './store/update-store.component';
import { CreateStoreComponent } from './store/create-store.component';
import { OrderComponent } from './order/order.component';
import { UpdateOrderComponent } from './order/update-order.component';
import { CreateOrderComponent } from './order/create-order.component';
import { ProductComponent } from './product/product.component';
import { UpdateProductComponent } from './product/update-product.component';
import { CreateProductComponent } from './product/create-product.component';
import { OrderItemComponent } from './order-item/order-item.component';
import { UpdateOrderItemComponent } from './order-item/update-order-item.component';
import { CreateOrderItemComponent } from './order-item/create-order-item.component';
import { ProductReviewComponent } from './product-review/product-review.component';
import { UpdateProductReviewComponent } from './product-review/update-product-review.component';
import { CreateProductReviewComponent } from './product-review/create-product-review.component';


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UpdateUserComponent,
    CreateUsersComponent,
    BrandComponent,
    UpdateBrandComponent,
    CreateBrandComponent,
    StoreComponent,
    UpdateStoreComponent,
    CreateStoreComponent,
    OrderComponent,
    UpdateOrderComponent,
    CreateOrderComponent,
    ProductComponent,
    UpdateProductComponent,
    CreateProductComponent,
    OrderItemComponent,
    UpdateOrderItemComponent,
    CreateOrderItemComponent,
    ProductReviewComponent,
    UpdateProductReviewComponent,
    CreateProductReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
