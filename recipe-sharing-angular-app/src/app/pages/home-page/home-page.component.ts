import { Component } from '@angular/core';
import { RecipeCardComponent } from "../recipe-card/recipe-card.component";
import {MatIconModule} from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { CreateRecipeFormComponent } from '../create-recipe-form/create-recipe-form.component';
import { AuthService } from '../../services/Auth/auth.service';
import { RecipeService } from '../../services/recipe/recipe.service';

@Component({
    selector: 'app-home-page',
    standalone: true,
    templateUrl: './home-page.component.html',
    styleUrl: './home-page.component.scss',
    imports: [RecipeCardComponent, MatIconModule, MatButtonModule]
})
export class HomePageComponent {
    constructor(public dialog: MatDialog, public authService: AuthService, public recipeService: RecipeService) {}
    recipes = [1,1,1,1,11,1];
    handleOpenCreateForm(){
        this.dialog.open(CreateRecipeFormComponent);
    }

    ngOnInit(){
        this.authService.getUserProfile();
        this.recipeService.getRecipes().subscribe();
        this.recipeService.recipeSubject.subscribe(
            (data) =>{
                this.recipes = data.recipes;
                console.log("data recipes:::", data.recipes)
            }
        )
        console.log("recipesList::::", this.recipes)
    }
}
