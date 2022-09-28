import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { ProductRoutingModule } from './product-routing.module';
import { AccordionModule } from 'ngx-bootstrap/accordion';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductListItemComponent } from './product-list-item/product-list-item.component';
import { FormsModule } from '@angular/forms';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { ProductListItemDetailsComponent } from './product-list-item-details/product-list-item-details.component';

@NgModule({
  declarations: [
    ProductListComponent,
    ProductListItemComponent,
    ProductListItemDetailsComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    ProductRoutingModule,
    AccordionModule,
    FormsModule,
    ButtonsModule,
  ]
})
export class ProductModule {}
