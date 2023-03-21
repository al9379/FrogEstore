import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';

import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit{
  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router, private cartService: CartService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productService.getProducts()
    .subscribe(products => this.products = products);
  }

  addToCart(id: number) : void {
    let user = localStorage.getItem('username');
    if (typeof user=='string') {
      this.cartService.getCart(user).subscribe(cart => {
        cart.products.push(id);
        console.log(cart.products);
        this.cartService.updateCart(cart).subscribe();
      });
    } else {
      this.router.navigate(['login']);
    }
  }  
}