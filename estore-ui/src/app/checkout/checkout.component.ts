import { Component } from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";
import {CartService} from "../cart.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {
  products: Product[] = []
  total: number = 0

  constructor(
    private productService: ProductService,
    private router: Router,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.total = 0
    let user = localStorage.getItem('username')
    if (typeof user != 'string') return
    this.cartService.getCart(user).subscribe(cart => {
      cart.products.forEach((id => {
        this.productService.getProduct(id).subscribe(product => {
          this.products.push(product)
          this.total = Math.round((this.total + product.price) * 100)/100
        })
      }))
    })
  }

  checkEmptyCart(): boolean {
    return this.products.length == 0
  }

  checkout(): void {
    let user = localStorage.getItem('username')
    if (typeof user != 'string') return
    this.products = []
    this.total = 0
    this.cartService.getCart(user).subscribe(cart => {
      cart.products = []
      this.cartService.updateCart(cart).subscribe()
    })
  }
}
