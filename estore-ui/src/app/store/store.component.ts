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
  allProducts: Product[] = [];

  constructor(
    private productService: ProductService,
    private router: Router,
    private cartService: CartService,
  ) { }

  ngOnInit(): void {
    this.productService.getProducts().subscribe(products => {
      this.allProducts = products;
      this.products = products;
    });
  }

  getProducts(): void {
    this.productService.getProducts().subscribe(products => this.allProducts = products);
  }

  search(searchValue:string) : void {
    this.productService.searchProducts(searchValue).subscribe(products => {
      if (searchValue.length > 0) {
        this.products = products;
      } else {
        this.products = this.allProducts;
      }
    });
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

  addReview(reviewText : string, id : number) : void {
    reviewText = reviewText.trim();
    if(reviewText.length == 0) return;
    let user = localStorage.getItem('username');
    let existing = this.products.find(product => product.id == id)
    if (existing) existing.reviews.push(`${user} said: ${reviewText}`);
    if(typeof user == 'string') {
      this.productService.getProduct(id).subscribe(product => {
        product.reviews.push(user + " said: " + reviewText)
        this.productService.updateProduct(product).subscribe();
      });
    }
    else {
      this.router.navigate(['login']);
    }
  }
}
