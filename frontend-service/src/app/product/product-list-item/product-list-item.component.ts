import { Component, Input, OnInit } from '@angular/core';
import { Product } from '../../shared/models/product';
import {AccountService} from "../../account/account.service";
import {ToastComponent} from "../../shared/components/toast/toast.component";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-list-item',
  templateUrl: './product-list-item.component.html',
  styleUrls: ['./product-list-item.component.scss'],
})
export class ProductListItemComponent implements OnInit {
  @Input() product: Product;
  quantity = 0;

  constructor(private accountService: AccountService,
              private router: Router,
              private service: ProductService, public toast: ToastComponent) {}

  ngOnInit(): void {}

  reserve(product: Product) {
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

    // this.router.navigate(['/payment']);
  }
}
