import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private baseUrl= "http://localhost:9090";
  constructor(private http: HttpClient) { }
  recipeSubject = new BehaviorSubject<any>({
    recipes:[],
    loading:false,
    newRecipe: null
  })

  private getHeader():HttpHeaders{
    const token = localStorage.getItem("jwt");
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`  // getting data from Authorization and storing into local storage 
    })
  }

  getRecipes():Observable<any>{
    const headers = this.getHeader();
    return this.http.get<any>(`${this.baseUrl}/api/v1/recipe/allRecipes`, {headers}).pipe(
      tap( (recipes) => {
        const currentState = this.recipeSubject.value;
        this.recipeSubject.next({...currentState, recipes}); // destructuring using javascript
        console.log("recipeSubject from service : ", this.recipeSubject);
      })
    )
  }

  createRecipes(recipe:any):Observable<any>{
    const headers = this.getHeader();
    return this.http.post<any>(`${this.baseUrl}/api/v1/recipe`,recipe, {headers}).pipe(
      tap( (newRecipe) => {
        const currentState = this.recipeSubject.value;
        this.recipeSubject.next({...currentState, recipes:[newRecipe, ...currentState.recipes]}); // destructuring using javascript
      })
    )
  }

  updateRecipes(recipe: any):Observable<any>{
    const headers = this.getHeader();
    return this.http.put<any>(`${this.baseUrl}/api/v1/recipe/update/${recipe.id}`,recipe,  {headers}).pipe(
      tap( (updateRedipe: any) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item:any) => item.id === updateRedipe.id ? updateRedipe : item)
        this.recipeSubject.next({...currentState, recipes:updatedRecipes}); // destructuring using javascript
      })
    )
  }

  deleteRecipes(id: any):Observable<any>{
    const headers = this.getHeader();
    return this.http.delete<any>(`${this.baseUrl}/api/v1/recipe/delete/${id}`,  {headers}).pipe(
      tap( (deletedRecipe: any) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item:any) => item.id !== id)
        this.recipeSubject.next({...currentState, recipes:updatedRecipes}); // destructuring using javascript
      })
    )
  }

  likeRecipes(id: any):Observable<any>{
    const headers = this.getHeader();
    return this.http.put<any>(`${this.baseUrl}/api/v1/recipe/like/${id}`,  {headers}).pipe(
      tap( (updateRedipe: any) => {
        const currentState = this.recipeSubject.value;
        const updatedRecipes = currentState.recipes.map((item:any) => item.id === updateRedipe.id ? updateRedipe : item)
        this.recipeSubject.next({...currentState, recipes:updatedRecipes}); // destructuring using javascript
      })
    )
  }

}
