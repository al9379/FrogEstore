import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frog Inc.';

  admin() : boolean {
    return localStorage.getItem('username')=='admin';
  }

  loggedIn() : boolean {
    return localStorage.getItem('username')!=undefined;
  }

  userBanner() :string{
    if (localStorage.getItem('username')!=undefined) {
      return "" + localStorage.getItem('username');
    } else {
      return "";
    }
  }
}