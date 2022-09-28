export interface Variant {
  color: string;
  size: string[];
  quantity: number;
}

export interface Product {
  id: string;
  name: string;
  price: number;
  available: boolean;
  variants: Variant[];
  createdAt: Date;
  updatedAt: Date;
}
