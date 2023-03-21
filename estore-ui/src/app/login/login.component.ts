import { Component, OnInit } from '@angular/core';
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
    private messageService: MessageService
  ){}

  loggedIn : boolean = false;

  ngOnInit(): void {
      if (localStorage.getItem('username')!=undefined) {
        this.login
      }
  }

  

  login(username : string) : void {
    this.userService.getUser(username).subscribe(user => {
      this.messageService.add(`Request for user '${user}'`);
      if(user == username) {
        localStorage.setItem('username', user);
        this.loggedIn = true;
      } else {
        this.userService.addUser(username).subscribe(newUser => {
          localStorage.setItem('username', newUser);
          this.loggedIn = true;
          
        });
      }
    });
  }

  logout() : void {
    localStorage.removeItem('username');
    this.loggedIn = false;
  }
}
