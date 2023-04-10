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
  productCount: Product[] = []; 

  constructor(private productService: ProductService, private router: Router, private cartService: CartService) { }

  ngOnInit(): void {
    let user = localStorage.getItem('username');
    if (typeof user != 'string') return;
    this.cartService.getCart(user).subscribe(cart => {
      cart.products.forEach(id => {
        this.productService.getProduct(id).subscribe(product => {
          this.products.push(product);
          let cexists = this.productCount.find(p => p.id == product.id);
          if (cexists) {
            cexists.stock += 1;
          } else {
            cexists = product;
            cexists.stock = 1;
            this.productCount.push(cexists);
          }
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
        let edited = this.productCount.find(p => p.id == id);
        if (edited) {
          edited.stock -= 1;
          if (edited.stock==0) {
            this.productCount = this.productCount.filter(p => p.id != id);
          }
        }
        this.cartService.updateCart(cart).subscribe(cart => {
        });
      });
    }
  }

  addToCart(id: number) : void {
    let user = localStorage.getItem('username');
    if (typeof user=='string') {
      this.cartService.getCart(user).subscribe(cart => {
        cart.products.push(id);
        
        this.cartService.updateCart(cart).subscribe(_ => {
          let exists = this.productCount.find(p => p.id == id) || {stock : 0};
          exists.stock += 1;
        });
      });
    } else {
      this.router.navigate(['login']);
    }
  }
}
