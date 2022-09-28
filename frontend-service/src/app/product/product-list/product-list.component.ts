import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../../shared/models/product';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { LoggerService } from '../../shared/service/logger.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  products: Product[];
  isLoading = true;

  constructor(
    private service: ProductService,
    private loggerService: LoggerService,
    public toast: ToastComponent
  ) {}

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.service.getProducts().subscribe(
      (data) => {
        console.log(this.products)
        this.products = data.content;
      },
      (error) => this.loggerService.logError('ProductListComponent', error.error),
      () => (this.isLoading = false)
    );
  }
}
