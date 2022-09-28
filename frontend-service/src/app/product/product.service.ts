import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../shared/models/product';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getProducts(): Observable<any> {
    return this.http.get<any>(`${environment.baseUrl}/catalogs`);
  }

  getProductById(id: string): Observable<Product> {
    return this.http.get<Product>(`/api/v1/product/${id}`);
  }
}
