import { Component } from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";
import {CartService} from "../cart.service";
import { Cart } from '../cart';

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
    let user = localStorage.getItem('username')
    if (typeof user != 'string') return
    this.cartService.getCart(user).subscribe(cart => {
      cart.products.forEach((id => {
        this.productService.getProduct(id).subscribe(product => {
          this.products.push(product)
        })
      }))
    })
    this.total = 0
    this.products.forEach(product => this.total += product.price)
  }

  checkout(): void {
    let user = localStorage.getItem('username')
    if (typeof user != 'string') return
    this.cartService.getCart(user).subscribe(cart => {
      cart.products = []
      this.cartService.updateCart(cart).subscribe()
    })
  }
}
