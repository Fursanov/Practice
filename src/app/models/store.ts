import { Brand } from "./brand";

export class Product{
  productId!: number;
  name!: string;
  brand!: Brand;
}
export class Store {
  storeId!: number;
  storeName!: string;
  location!: string;
  products!: Product[];
}
