import { Component, Input } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { UpdateRecipeFormComponent } from '../update-recipe-form/update-recipe-form.component';
import { RecipeService } from '../../services/recipe/recipe.service';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatCardModule],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss'
})
export class RecipeCardComponent {

  @Input() recipe:any;
  constructor(public dialog: MatDialog, private recipeService:RecipeService) {}


  handleOpenUpdateRecipe(){
    this.dialog.open(UpdateRecipeFormComponent, {data:this.recipe});
    // this.dialog.open(UpdateRecipeFormComponent, {data: this.recipe ,});
  }

  handleDeleteRecipe(){
    this.recipeService.updateRecipes(this.recipe.id).subscribe();
    window.location.reload();
  }
  ngOnInit(){
    console.log("user details::: ", this.recipe.user)
  }

}
