package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
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

        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set> captor = ArgumentCaptor.forClass(Set.class); //model addAttribute method takes set of recipes as argument

        //when
        String result = indexController.getIndexPage(model);


        //then
        verify(recipeService, times(1)).getRecipes(); //when getIndexPage is called, verify getRecipes is called once

        //verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes());

        verify(model, times(1)).addAttribute(eq("recipes"), anySet()); //when getIndexPage is called, verify addAttribute is called once

        //capture argument passed to model addattribute method. verify the addAttribute method is called exactly once.
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture()); //capture the argument that is passed to addAttribute method

        Set<Recipe> resultSet = captor.getValue();

        assertEquals(1, resultSet.size()); //verify the argument size is 1 as expected

        assertEquals("index", result);
        
    }
}