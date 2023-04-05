import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl ='http://'+window.location.hostname+':8080/users';  // URL to web api 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService){}


  getUser(username: string): Observable<string> {
    const url = `${this.usersUrl}/${username}`;
    return this.http.get(url, {responseType:'text'}).pipe(
      tap(_ => this.log(`fetched user username=${username}`)),
      catchError(this.handleError<string>(`getUser id=${username}`))
    );
  }
  addUser(username: string): Observable<string> {
    return this.http.post(this.usersUrl, username, {responseType:'text'}).pipe(
      tap(_ => this.log(`added user w/ username=${username}`)),
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
