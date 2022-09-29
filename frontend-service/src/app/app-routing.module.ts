import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService as AuthGuard } from '../app/core/auth/auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: '/product', pathMatch: 'full' },
  {
    path: 'product',
    loadChildren: () =>
      import('./product/product.module').then((module) => module.ProductModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'payment',
    loadChildren: () =>
      import('./shopping-cart/shopping-cart.module').then(
        (module) => module.ShoppingCartModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'notification',
    loadChildren: () =>
      import('./notification/notification.module').then(
        (module) => module.NotificationModule),
    canActivate: [AuthGuard],
  },
  {
    path: 'account',
    loadChildren: () =>
      import('./account/account.module').then((module) => module.AccountModule),
  },
  { path: '**', redirectTo: 'not-found', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
