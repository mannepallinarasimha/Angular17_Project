import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl= "http://localhost:9090";
  constructor(private http: HttpClient) { }
  authSubject = new BehaviorSubject<any>({
    user:null
  })
  loginAction(userData: any): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/auth/signIn`, userData);
  }
  registerAction(userData: any): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/auth/signUp`, userData);
  }

  getUserProfile():Observable<any>{
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`  // getting data from Authorization and storing into local storage 
    })
    return this.http.get<any>(`${this.baseUrl}/api/v1/user/profile`, {headers}).pipe( 

      tap( (user) => {
        const currentState = this.authSubject.value;
        this.authSubject.next({...currentState, user}); // destructuring using javascript
      })
    );
  }

  logout(){
    localStorage.clear();
    this.authSubject.next({})
  }
  }
