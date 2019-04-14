package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipes);

        String result = indexController.getIndexPage(model);

        verify(recipeService, times(1)).getRecipes();

        //verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes());

        verify(model, times(1)).addAttribute(eq("recipes"), anySet());

        assertEquals("index", result);



    }
}