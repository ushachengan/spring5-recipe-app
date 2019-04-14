package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    RecipeServiceImpl recipeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {

        List<Recipe> recipes = new ArrayList<Recipe>();
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipes);

        final Set<Recipe> result = recipeService.getRecipes();

        assertEquals(1, result.size());

        //verify findAll method in recipeRepository is called
        verify(recipeRepository, times(1)).findAll();
    }
}