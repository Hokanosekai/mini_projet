package fr.hoka.repositories;

import fr.hoka.models.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     *
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
     *
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

    public String getSugarQuantityInZuppaIngleseRecipe() {
        return this.recipes
                .stream()
                .filter(recipe -> recipe.getTitle().equals("Zuppa Inglese"))
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
                .filter(ingredient -> ingredient.getName().contains("sugar"))
                .map(ingredient -> {
                    assert ingredient instanceof SimpleIngredient;
                    return ((SimpleIngredient) ingredient).getAmount();
                })
                .map(Double::parseDouble)
                .reduce(0D, Double::sum)
                .toString();
    }

    public List<Step> getTwoFirstStepsOfZuppaIngleseRecipe() {
        return this.recipes
                .stream()
                .filter(recipe -> recipe.getTitle().equals("Zuppa Inglese"))
                .map(Recipe::getSteps)
                .flatMap(List::stream)
                .limit(2)
                .toList();
    }

    /**
     * Get the recipes with more than 5 steps
     * (don't forget to count the steps of the composite ingredients)
     *
     * @return List of recipes
     */
    public List<Recipe> getRecipesWithMoreThanFiveSteps() {
        return this.recipes
                .stream()
                .filter(recipe -> recipe.getSteps().size() + recipe.getIngredients()
                        .stream()
                        .filter(ingredient -> ingredient instanceof CompositeIngredient)
                        .map(ingredient -> (CompositeIngredient) ingredient)
                        .map(CompositeIngredient::getSteps)
                        .mapToLong(List::size)
                        .sum() > 5
                )
                .toList();
    }

    /**
     * Get the recipes without butter
     *
     * @return List of recipes
     */
    public List<Recipe> getRecipesWithoutButter() {
        return this.recipes
                .stream()
                .filter(recipe -> recipe.getIngredients()
                        .stream()
                        .noneMatch(ingredient -> ingredient.getName().contains("butter"))
                )
                .toList();
    }

    /**
     * Get the recipes that have an ingredient in common with the recipe "Zuppa Inglese"
     *
     * @return List of recipes
     */
    public List<Recipe> getRecipesWithIngredientInCommonWithZuppaInglese() {
        return this.recipes
                .stream()
                .filter(recipe -> recipe.getIngredients()
                        .stream()
                        .anyMatch(ingredient -> {
                            if (ingredient instanceof CompositeIngredient) {
                                return ((CompositeIngredient) ingredient).getIngredients()
                                        .stream()
                                        .anyMatch(complexIngredient -> {
                                            return this.recipes
                                                    .stream()
                                                    .filter(recipe1 -> recipe1.getTitle().equals("Zuppa Inglese"))
                                                    .map(Recipe::getIngredients)
                                                    .flatMap(List::stream)
                                                    .anyMatch(ingredient1 -> ingredient1.getName().equals(complexIngredient.getName()));
                                        });
                            } else {
                                return this.recipes
                                        .stream()
                                        .filter(recipe1 -> recipe1.getTitle().equals("Zuppa Inglese"))
                                        .map(Recipe::getIngredients)
                                        .flatMap(List::stream)
                                        .anyMatch(ingredient1 -> ingredient1.getName().equals(ingredient.getName()));
                            }
                        })
                )
                .toList();
    }

    /**
     * Get the recipe with the most calories
     * @return Recipe
     */
    public Recipe getRecipeWithMostCalories() {
        return this.recipes
                .stream()
                .max(Comparator.comparing(Recipe::getCalories))
                .orElse(null);

    }

    /**
     * Get the most frequent unit of measurement
     * @return String
     */
    public String getMostFrequentUnitOfMeasurement() {
        return this.recipes
                .stream()
                .map(Recipe::getIngredients)
                .flatMap(List::stream)
                .map(ingredient -> {
                    if (ingredient instanceof SimpleIngredient) {
                        return ingredient;
                    } else if (ingredient instanceof CompositeIngredient) {
                        return ((CompositeIngredient) ingredient).getIngredients()
                                .stream()
                                .map(ingredient1 -> {
                                    if (ingredient1 instanceof SimpleIngredient) {
                                        return ingredient1;
                                    } else {
                                        return null;
                                    }
                                })
                                .filter(Objects::nonNull)
                                .findFirst()
                                .orElse(null);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(ingredient -> {
                    assert ingredient instanceof SimpleIngredient;
                    if (((SimpleIngredient) ingredient).getUnit() == null) {
                        return "null";
                    }
                    return ((SimpleIngredient) ingredient).getUnit().getUnit();
                })
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Get the number of ingredients per recipe
     * @return Map<Recipe, Integer>
     */
    public Map<Recipe, Integer> getNumberOfIngredientsPerRecipe() {
        return this.recipes
                .stream()
                .collect(
                        Collectors.toMap(
                                recipe -> recipe,
                                recipe -> recipe.getIngredients()
                                        .stream()
                                        .mapToInt(ingredient -> {
                                            if (ingredient instanceof SimpleIngredient) {
                                                return 1;
                                            } else {
                                                return ((CompositeIngredient) ingredient).getIngredients().size();
                                            }
                                        })
                                        .sum()
                        )
                );
    }

    /**
     * Get the recipe with the most fat
     * (We will use compare fat from the Nutrition field)
     * @return Recipe
     */
    public Recipe getRecipeWithMostFat() {
        return this.recipes
                .stream()
                .max(Comparator.comparing(recipe -> recipe.getNutrition().getFats()))
                .orElse(null);
    }

    /**
     * Get the most frequent ingredient
     * (Don't forget to count the ingredients of the composite ingredients)
     * (We will use the amount of the ingredient to compare)
     * (For CompositeIngredient we will count 1 ingredient)
     * @return Ingredient
     */
    public Ingredient getMostFrequentIngredient() {
        return this.recipes
                .stream()
                .map(Recipe::getIngredients)
                .flatMap(List::stream)
                .map(ingredient -> {
                    if (ingredient instanceof SimpleIngredient) {
                        return ingredient;
                    } else if (ingredient instanceof CompositeIngredient) {
                        return ((CompositeIngredient) ingredient).getIngredients()
                                .stream()
                                .map(ingredient1 -> {
                                    if (ingredient1 instanceof SimpleIngredient) {
                                        return ingredient1;
                                    } else {
                                        return null;
                                    }
                                })
                                .filter(Objects::nonNull)
                                .findFirst()
                                .orElse(null);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingDouble(ingredient -> {
                    if (ingredient instanceof SimpleIngredient) {
                        if (((SimpleIngredient) ingredient).getAmount() == null || ((SimpleIngredient) ingredient).getAmount().equals("*")) {
                            return 1;
                        }
                        return Double.parseDouble(((SimpleIngredient) ingredient).getAmount());
                    } else {
                        return 1;
                    }
                })))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Get recipes ordered by number of ingredients
     * @return List of recipes
     */
    public List<Recipe> getRecipesOrderedByNumberOfIngredients() {
        return this.recipes
                .stream()
                .sorted(Comparator.comparing(recipe -> recipe.getIngredients()
                        .stream()
                        .mapToInt(ingredient -> {
                            if (ingredient instanceof SimpleIngredient) {
                                return 1;
                            } else {
                                return ((CompositeIngredient) ingredient).getIngredients().size();
                            }
                        })
                        .sum()
                ))
                .toList();
    }

    /**
     * Get for each ingredient the recipes that contain it
     * @return Map<Ingredient, List<Recipe>>
     */
    public Map<Ingredient, List<Recipe>> getRecipesPerIngredient() {
        return this.recipes
                .stream()
                .map(recipe -> {
                    List<Ingredient> ingredients = new ArrayList<>();
                    ingredients.addAll(recipe.getIngredients());
                    ingredients.addAll(recipe.getIngredients()
                            .stream()
                            .filter(ingredient -> ingredient instanceof CompositeIngredient)
                            .map(ingredient -> ((CompositeIngredient) ingredient).getIngredients())
                            .flatMap(List::stream)
                            .toList());
                    return Map.entry(recipe, ingredients);
                })
                .flatMap(entry -> entry.getValue()
                        .stream()
                        .map(ingredient -> Map.entry(ingredient, entry.getKey()))
                )
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    /** La 21 jai pas compris
     * Get the recipe with the most calories
     * @return Recipe
     */

    /**
     * Get the recipe most easy to prepare (the one with the least steps)
     * (Don't forget to count the steps of the composite ingredients)
     * @return Recipe
     */
    public Recipe getRecipeMostEasyToPrepare() {
        return this.recipes
                .stream()
                .min(Comparator.comparing(recipe -> recipe.getIngredients()
                        .stream()
                        .mapToInt(ingredient -> {
                            if (ingredient instanceof SimpleIngredient) {
                                return 1;
                            } else {
                                return ((CompositeIngredient) ingredient).getIngredients().size();
                            }
                        })
                        .sum()
                ))
                .orElse(null);
    }
}
