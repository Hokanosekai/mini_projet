package fr.hoka.repositories;

import fr.hoka.models.CompositeIngredient;
import fr.hoka.models.Recipe;
import fr.hoka.models.SimpleIngredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecipeRepository {
    private List<Recipe> recipes = new ArrayList<>();

    public RecipeRepository() {

    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    /**
     * Get the titles of all recipes
     * @return List of strings
     */
    public List<String> getRecipeTitles() {
        return this.recipes
                .stream()
                .map(Recipe::getTitle)
                .toList();
    }

    /**
     * Get the total amount of eggs in all recipes
     * @return Integer
     */
    public Integer getTotalAmountOfEggs() {
        return this.recipes
                .stream()
                .map(Recipe::getIngredients)
                .flatMap(List::stream)
                .map(ingredient -> {
                    if (ingredient instanceof SimpleIngredient) {
                        return ingredient;
                    } else if (ingredient instanceof CompositeIngredient) {
                        return ingredient;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(ingredient -> ingredient.getName().contains("egg"))
                .map(ingredient -> {
                    assert ingredient instanceof SimpleIngredient;
                    return ((SimpleIngredient) ingredient).getAmount();
                })
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    public List<Recipe> getRecipesWithOliveOil() {
        return this.recipes
            .stream()
            .filter(recipe -> recipe.getIngredients()
                .stream()
                .anyMatch(ingredient -> {
                    if (ingredient instanceof CompositeIngredient) {
                        return ((CompositeIngredient) ingredient).getIngredients()
                            .stream()
                            .anyMatch(complexIngredient -> complexIngredient.getName().contains("olive oil"));
                    } else {
                        return ingredient.getName().contains("olive oil");
                    }
                })
            )
            .toList();
    }

    public Map<Recipe, Integer> getEggsPerRecipe() {
        return this.recipes
            .stream()
            .collect(
                Collectors.toMap(
                    recipe -> recipe,
                    recipe -> recipe.getIngredients()
                        .stream()
                        .map(ingredient -> {
                            if (ingredient instanceof SimpleIngredient) {
                                return ingredient;
                            } else if (ingredient instanceof CompositeIngredient) {
                                return ingredient;
                            } else {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .filter(ingredient -> ingredient.getName().contains("egg"))
                        .map(ingredient -> {
                            assert ingredient instanceof SimpleIngredient;
                            return ((SimpleIngredient) ingredient).getAmount();
                        })
                        .map(Integer::parseInt)
                        .reduce(0, Integer::sum)
                )
            );
    }

    public List<Recipe> getRecipesWithLessThan500Calories() {
        return this.recipes
            .stream()
            .filter(recipe -> recipe.getCalories() < 500)
            .toList();
    }
}
