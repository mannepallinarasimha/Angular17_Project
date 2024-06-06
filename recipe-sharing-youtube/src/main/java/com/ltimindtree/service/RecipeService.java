package com.ltimindtree.service;

import java.util.List;

import com.ltimindtree.entity.Recipe;

public interface RecipeService {

	public Recipe createRecipe(Recipe recipe, String jwt)throws Exception;
	public Recipe findRecipeById(Long id) throws Exception;
	public List<Recipe> getAllRecipies()throws Exception;
	public Recipe updateRecipe(Recipe recipe, Long id)throws Exception;
	public String deleteRecipe(Long id)throws Exception;
	public Recipe likeRecipe(Long recipeId, String jwt)throws Exception;
}
