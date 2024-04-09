export class Brand {
  brandId!: number;
  brandName!: string;
}

export class Store {
  storeId!: number;
}
export class Product {
  productId!: number;
  name!: string;
  description!: string;
  price!: number;
  stockQuantity!: number;
  brand!: Brand;
  stores!: Store[];
}
