export class Product {
  productId!: number;
}
export class Store {
  storeId!: number;
  storeName!: string;
  location!: string;
  products!: Product[];
}
