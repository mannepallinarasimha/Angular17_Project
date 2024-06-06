package com.ltimindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
