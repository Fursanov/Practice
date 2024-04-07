import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {UpdateUserComponent} from "./users/update-users.component";
import {CreateUsersComponent} from "./users/create-users.component";
import { UpdateBrandComponent } from './brand/update-brand.component';
import { CreateBrandComponent } from './brand/create-brand.component';
import { BrandComponent } from './brand/brand.component';
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

const routes: Routes = [
  {path: "product-reviews", component: ProductReviewComponent},
  {path: "update-product-review/:id", component: UpdateProductReviewComponent},
  {path: "create-product-review", component: CreateProductReviewComponent},
  {path: "order-items", component: OrderItemComponent},
  {path: "update-order-item/:id", component: UpdateOrderItemComponent},
  {path: "create-order-item", component: CreateOrderItemComponent},
  {path: "users", component: UsersComponent},
  {path: "update-user/:id", component: UpdateUserComponent},
  {path: "create-user", component: CreateUsersComponent},
  {path: "brands", component: BrandComponent},
  {path: "update-brand/:id", component: UpdateBrandComponent},
  {path: "create-brand", component: CreateBrandComponent},
  {path: "stores", component: StoreComponent},
  {path: "update-store/:id", component: UpdateStoreComponent},
  {path: "create-store", component: CreateStoreComponent},
  {path: "orders", component: OrderComponent},
  {path: "update-order/:id", component: UpdateOrderComponent},
  {path: "create-order", component: CreateOrderComponent},
  {path: "products", component: ProductComponent},
  {path: "update-product/:id", component: UpdateProductComponent},
  {path: "create-product", component: CreateProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
