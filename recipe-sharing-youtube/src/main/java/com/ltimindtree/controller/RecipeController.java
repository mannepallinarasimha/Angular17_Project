package com.ltimindtree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.entity.Recipe;
import com.ltimindtree.service.RecipeService;

@RestController
@RequestMapping(path="api/v1/recipe")
//@CrossOrigin(origins = {"http://localhost:4200"})
public class RecipeController {
    @Autowired
	private RecipeService recipeService;
    
    
    @PostMapping
	public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt)throws Exception {
		return recipeService.createRecipe(recipe, jwt);
	}
    
    @GetMapping("get/{id}")
    public Recipe getRecipeById(@PathVariable("id") Long id) throws Exception {
    	return recipeService.findRecipeById(id);
    }
    
    @DeleteMapping("delete/{id}")
    public String deleteRecipe(@PathVariable("id") Long id) throws Exception {
    	return recipeService.deleteRecipe(id);
    }
    
    @GetMapping("allRecipes")
    public List<Recipe> getAllRecipies() throws Exception{
    	return recipeService.getAllRecipies();
    }
    
    @GetMapping("/like/{id}")
    public Recipe likedRecipe(@PathVariable("id")Long id, @RequestHeader("Authorization") String jwt) throws Exception {
    	return recipeService.likeRecipe(id, jwt);
    }
    
    @PutMapping("update/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable("id")Long id) throws Exception {
    	return recipeService.updateRecipe(recipe, id);
    }
}
