package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    Ingredient ingredient;

    @Before
    public void setup() {
        ingredient = new Ingredient();
    }

    @Test
    public void getId() {

        Long id = 1L;

        ingredient.setId(id);

        assertEquals(id, ingredient.getId());
    }
}