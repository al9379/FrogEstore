import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  products: Product[] = [];

  constructor(private productService: ProductService, private router: Router, private cartService: CartService) { }

  ngOnInit(): void {
    let user = localStorage.getItem('username');
    if (typeof user != 'string') return;
    this.cartService.getCart(user).subscribe(cart => {
      cart.products.forEach(id => {
        this.productService.getProduct(id).subscribe(product => {
          this.products.push(product);
        })
      });
    });
  }

  removeFromCart(id:number) : void {
    let user = localStorage.getItem('username');
    if (typeof user=='string') {
      this.cartService.getCart(user).subscribe(cart => {
        for(let i=0; i<cart.products.length; i++) {
          if (cart.products[i]==id) {
            this.products=this.products.slice(0,i).concat(this.products.slice(i+1,cart.products.length));
            break;
          }
        }
        cart.products=this.products.map(product => product.id);
        this.cartService.updateCart(cart).subscribe();
      });
    }
  }
}
