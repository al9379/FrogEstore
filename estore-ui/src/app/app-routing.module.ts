import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductsComponent } from './products/products.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { LoginComponent } from './login/login.component';
import { StoreComponent } from './store/store.component';
import { CartComponent } from './cart/cart.component';
import {CheckoutComponent} from "./checkout/checkout.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch:'full'},
  { path: 'login', component: LoginComponent},
  { path: 'detail/:id', component: ProductDetailComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'store', component: StoreComponent},
  { path: 'cart', component: CartComponent},
  { path: 'checkout', component: CheckoutComponent}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
