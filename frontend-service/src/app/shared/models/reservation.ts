import {Product} from "./product";
export interface ReservationItem {
  id: string;
  reservationStatus: string;
  vehicle: Product;
}

export interface ReservationResponse {
  reservationItems: ReservationItem[];
}
