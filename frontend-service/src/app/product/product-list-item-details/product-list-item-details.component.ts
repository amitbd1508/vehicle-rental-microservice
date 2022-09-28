import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { Product, Variant } from '../../shared/models/product';
import { MessengerService } from '../../shared/service/messenger.service';
import { CartService } from '../../shopping-cart/cart.service';
import { CartItem } from '../../shared/models/cart';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { LoggerService } from '../../shared/service/logger.service';

@Component({
  selector: 'app-product-list-item-details',
  templateUrl: './product-list-item-details.component.html',
})
export class ProductListItemDetailsComponent implements OnInit {
  product: Product = null;
  quantity = 1;

  selectedVariant: Variant;
  selectedVariantSize: string;

  isLoading = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private service: ProductService,
    private cartService: CartService,
    private loggerService: LoggerService,
    public toast: ToastComponent
  ) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.getById(id);
  }

  getById(id: string): void {
    this.service.getProductById(id).subscribe(
      (data) => {
        this.product = data;
        if (this.product.variants.length > 0) {
          this.selectedVariant = this.product.variants[0];
          this.selectedVariantSize = this.product.variants[0].size[0];
        }
        if (!this.product.available) {
          this.toast.setMessage(
            `Currently this  product is not available`,
            'info'
          );
        }
      },
      (error) => {
        this.loggerService.logError(
          'ProductListItemDetailsComponent',
          error.error.toString()
        );
        this.toast.setMessage('Product not found', 'danger', 3000);
        this.router.navigate(['/product']);
      },
      () => (this.isLoading = false)
    );
  }

  increaseQuantity(): void {
    if (this.quantity < this.selectedVariant.quantity) {
      this.quantity++;
    }
  }

  decreaseQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  handleAddToCart(product: Product): void {
    const cartItem: CartItem = {
      id: Math.floor(Math.random() * 1000000),
      productId: product.id,
      productPrice: product.price,
      productName: product.name,
      variantColor: this.selectedVariant.color,
      variantSize: this.selectedVariantSize,
      quantity: this.quantity,
      numberOfAvailableProduct: this.selectedVariant.quantity,
    };

    this.cartService.addProductToCart(cartItem);
    this.toast.setMessage(
      `Products is added to cart. Please go to cart for checkout !`,
      'warning',
      3000
    );

    this.router.navigate(['/product']);
  }

  selectColor(variant: Variant): void {
    this.selectedVariant = variant;
    this.selectedVariantSize = variant.size[0];
    this.quantity = 1;
  }

  selectVariantSize(size: string): void {
    this.selectedVariantSize = size;
  }
}
