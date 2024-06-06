package com.ltimindtree.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltimindtree.entity.Recipe;
import com.ltimindtree.entity.User;
import com.ltimindtree.repository.RecipeRepository;
import com.ltimindtree.repository.UserRepository;
import com.ltimindtree.service.RecipeService;
import com.ltimindtree.service.UserService;
@Service
public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Recipe createRecipe(Recipe recipe, String jwt) throws Exception {
		User findById = userService.findByJwt(jwt);
		Recipe newRecipe = new Recipe();
		newRecipe.setTitle(recipe.getTitle());
		newRecipe.setDescription(recipe.getDescription());
		newRecipe.setImage(recipe.getImage());
		newRecipe.setUser(findById);
		newRecipe.setCreatedAt(LocalDateTime.now());
		newRecipe.setVegitarien(false);
		
		return recipeRepository.save(newRecipe);
	}

	@Override
	public Recipe findRecipeById(Long id) throws Exception {
		return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe with ID "+id+" NOT found in DB"));
	}

	@Override
	public List<Recipe> getAllRecipies() throws Exception {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
		Recipe recipeFromDB = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe with ID "+id+" NOT found in DB"));
		
		if(recipe.getTitle() != null) {
			recipeFromDB.setTitle(recipe.getTitle());
		}
		if(recipe.getDescription() != null) {
			recipeFromDB.setDescription(recipe.getDescription());
		}
		if(recipe.getImage() != null) {
			recipeFromDB.setImage(recipe.getImage());
		}
		return recipeRepository.save(recipeFromDB);
	}

	@Override
	public String deleteRecipe(Long id) throws Exception {
		Recipe recipeFromDB = recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe with ID "+id+" NOT found in DB"));
		if(recipeFromDB != null) {
			recipeRepository.deleteById(id);
		}
		
		return "Recipe Deleted Successfully...";
	}

	@Override
	public Recipe likeRecipe(Long recipeId, String jwt) throws Exception {
		User findByJwt = userService.findByJwt(jwt);
		User userFromDB = userRepository.findById(findByJwt.getId()).orElseThrow(() -> new RuntimeException("User with id NOT present in DB"));
		Recipe recipeFromDB = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe with ID "+recipeId+" NOT found in DB"));
		if(recipeFromDB.getLikes().contains(userFromDB.getId())) {
			recipeFromDB.getLikes().remove(userFromDB.getId());
		}else {
			recipeFromDB.getLikes().add(userFromDB.getId());
		}
		return recipeRepository.save(recipeFromDB);
	}

}
