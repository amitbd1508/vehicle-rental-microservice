import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../../shared/models/product';
import { MessengerService } from '../../shared/service/messenger.service';
import { CartService } from '../../shopping-cart/cart.service';
import { CartItem } from '../../shared/models/cart';
import { ToastComponent } from '../../shared/components/toast/toast.component';
import { LoggerService } from '../../shared/service/logger.service';
import {AccountService} from "../../account/account.service";

@Component({
  selector: 'app-product-list-item-details',
  templateUrl: './product-list-item-details.component.html',
})
export class ProductListItemDetailsComponent implements OnInit {
  product: Product = null;
  quantity = 1;

  selectedVariantSize: string;

  isLoading = true;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private service: ProductService,
    private accountService: AccountService,
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
        if (this.product.vehicleStatus === 'RESERVED') {
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

  handleAddToCart(product: Product): void {
    // const cartItem: CartItem = {
    //   id: Math.floor(Math.random() * 1000000),
    //   productId: product.id,
    //   productPrice: product.price,
    //   productName: product.catalogName,
    //   variantColor: product.color,
    //   variantSize: this.selectedVariantSize,
    //   quantity: this.quantity,
    //   numberOfAvailableProduct: 1,
    // };
    //
    // this.cartService.addProductToCart(cartItem);
    // this.toast.setMessage(
    //   `Products is added to cart. Please go to cart for checkout !`,
    //   'warning',
    //   3000
    // );

    const token = localStorage.getItem('token');
    if(!token) {
      this.accountService.logout();
      this.toast.setMessage(`User is logged out by system`, 'danger');
    }
    const user = localStorage.getItem('user');
    if(!user){
      this.toast.setMessage(`User is logged out by system!`, 'danger');
    }
    const parseedUser = JSON.parse(user);
    const body = {
      account: {
        email: parseedUser.email,
        id: parseedUser.id
      },
      "duration": {
        "rentalDate": "10-10-2022",
        "returnDate": "11-11-2022"
      },
      "paymentType": "BANK"
    }

    this.service.reserveProduct(token, body, product.id).subscribe(data => {
      console.log("data ==", data);
      this.toast.setMessage(`Car is reserved for 24 hours please make the payment!`, 'success');

    });

    this.router.navigate(['/payment']);
  }
}
