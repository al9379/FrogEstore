import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ProductsComponent } from './products/products.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { MessagesComponent } from './messages/messages.component';
import { StoreComponent } from './store/store.component';
import { LoginComponent } from './login/login.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    MessagesComponent,
    ProductDetailComponent,
    ProductsComponent,
    ProductSearchComponent,
    StoreComponent,
    LoginComponent,
    CartComponent,
    CheckoutComponent,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }