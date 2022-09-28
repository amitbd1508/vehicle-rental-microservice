export interface CartItem {
  id: number;
  productId: string;
  productName: string;
  variantColor: string;
  variantSize: string;
  productPrice: number;
  quantity: number;
  numberOfAvailableProduct: number;
}

export interface CheckOutResponse {
  totalPrice: string;
}
