import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductListItemDetailsComponent } from './product-list-item-details/product-list-item-details.component';

const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: ':id', component: ProductListItemDetailsComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductRoutingModule {}
