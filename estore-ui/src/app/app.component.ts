import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frog Inc.';
  a = false;

  admin() : boolean {
    return localStorage.getItem('username')=='admin';
  }

  userBanner() :string{
    if (localStorage.getItem('username')!=undefined) {
      return "Hello " + localStorage.getItem('username');
    } else {
      return "Login required";
    }
  }
}