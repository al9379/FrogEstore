import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Cart } from './cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartsUrl = 'http://localhost:8080/carts';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService){}


  getCart(username: string): Observable<Cart> {
    const url = `${this.cartsUrl}/${username}`;
    return this.http.get<Cart>(url).pipe(
      tap(_ => this.log(`fetched cart username=${username}`)),
      catchError(this.handleError<Cart>(`getCart User=${username}`))
    );
  }
  addCart(username: string): Observable<string> {
    return this.http.post(`${this.cartsUrl}/${username}`, username, {responseType:'text'}).pipe(
      tap(_ => this.log(`added user w/ username=${username}`)),
      catchError(this.handleError<string>('addUser'))
    );
  }

  updateCart(cart: Cart) : Observable<string> {
    return this.http.put(`${this.cartsUrl}/${cart.username}`, cart, {...this.httpOptions,responseType:'text'}).pipe(
      tap(_ => this.log(`updated cart for ${cart.username}`)),
      catchError(this.handleError<string>('addUser'))
    );
  }


    /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`UsersService: ${message}`);
  }
}
