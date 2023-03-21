import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { MessageService } from '../message.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private cartService: CartService
  ){}

  loggedIn : boolean = false;

  ngOnInit(): void {
    this.loggedIn = localStorage.getItem('username') != undefined;
  }

  

  login(username : string) : void {
    if (username.length == 0) return;
    this.userService.getUser(username).subscribe(user => {
      this.messageService.add(`Request for user '${user}'`);
      if(user == username) {
        localStorage.setItem('username', user);
        this.loggedIn = true;
      } else {
        this.userService.addUser(username).subscribe(newUser => {
          localStorage.setItem('username', newUser);
          this.loggedIn = true;
          this.cartService.addCart(username).subscribe();
        });
      }
    });
  }

  logout() : void {
    localStorage.removeItem('username');
    this.loggedIn = false;
  }
}
