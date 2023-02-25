package fr.hoka;

import fr.hoka.models.Ingredient;
import fr.hoka.models.Recipe;
import fr.hoka.models.Step;
import fr.hoka.repositories.RecipeRepository;
import fr.hoka.utils.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Parser parser;
        RecipeRepository recipeRepository;

        try {
            parser = new Parser("recipes.utf-8.xml");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        try {
            recipeRepository = parser.parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("-------------------- RECIPES ---------------------");

        System.out.println("\n------------------- EXERCICE 4 -------------------");
        List<String> recipeTitles = recipeRepository.getRecipeTitles();
        recipeTitles.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 5 -------------------");
        Integer totalAmountOfEggs = recipeRepository.getTotalAmountOfEggs();
        System.out.println(totalAmountOfEggs);

        System.out.println("\n------------------- EXERCICE 6 -------------------");
        List<Recipe> recipesWithOliveOil = recipeRepository.getRecipesWithOliveOil();
        recipesWithOliveOil.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 7 -------------------");
        Map<Recipe, Integer> eggsPerRecipe = recipeRepository.getEggsPerRecipe();
        eggsPerRecipe.forEach((recipe, eggs) -> System.out.println(recipe + " : " + eggs));

        System.out.println("\n------------------- EXERCICE 8 -------------------");
        List<Recipe> recipesWithLessThan500Calories = recipeRepository.getRecipesWithLessThan500Calories();
        recipesWithLessThan500Calories.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 9 -------------------");
        String sugarQuantityInZuppaInglese = recipeRepository.getSugarQuantityInZuppaIngleseRecipe();
        System.out.println(sugarQuantityInZuppaInglese);

        System.out.println("\n------------------- EXERCICE 10 -------------------");
        List<Step> stepsInZuppaInglese = recipeRepository.getTwoFirstStepsOfZuppaIngleseRecipe();
        stepsInZuppaInglese.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 11 -------------------");
        List<Recipe> recipesWithMoreThanFiveSteps = recipeRepository.getRecipesWithMoreThanFiveSteps();
        recipesWithMoreThanFiveSteps.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 12 -------------------");
        List<Recipe> recipesWithoutButter = recipeRepository.getRecipesWithoutButter();
        recipesWithoutButter.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 13 ------------------");
        List<Recipe> recipesWithIngredientsInCommon = recipeRepository.getRecipesWithIngredientInCommonWithZuppaInglese();
        recipesWithIngredientsInCommon.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 14 ------------------");
        Recipe recipeWithMostCalories = recipeRepository.getRecipeWithMostCalories();
        System.out.println(recipeWithMostCalories);

        System.out.println("\n------------------- EXERCICE 15 ------------------");
        String mostFrequentMeasurementUnit = recipeRepository.getMostFrequentUnitOfMeasurement();
        System.out.println(mostFrequentMeasurementUnit);

        System.out.println("\n------------------- EXERCICE 16 ------------------");
        Map<Recipe, Integer> numberOfIngredientsPerRecipe = recipeRepository.getNumberOfIngredientsPerRecipe();
        numberOfIngredientsPerRecipe.forEach((recipe, numberOfIngredients) -> System.out.println(recipe + " : " + numberOfIngredients));

        System.out.println("\n------------------- EXERCICE 17 ------------------");
        Recipe recipeWithMostFat = recipeRepository.getRecipeWithMostFat();
        System.out.println(recipeWithMostFat);

        System.out.println("\n------------------- EXERCICE 18 ------------------");
        Ingredient mostUsedIngredient = recipeRepository.getMostFrequentIngredient();
        System.out.println(mostUsedIngredient.getName());

        System.out.println("\n------------------- EXERCICE 19 ------------------");
        List<Recipe> orderedRecipesByNumberOfIngredients = recipeRepository.getRecipesOrderedByNumberOfIngredients();
        orderedRecipesByNumberOfIngredients.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 20 ------------------");
        Map<Ingredient, List<Recipe>> recipesPerIngredient = recipeRepository.getRecipesPerIngredient();
        recipesPerIngredient.forEach((ingredient, recipes) -> System.out.println(ingredient + " : " + recipes));

        System.out.println("\n------------------- EXERCICE 21 ------------------");


        System.out.println("\n------------------- EXERCICE 22 ------------------");
        Recipe recipeMostEasilyPrepared = recipeRepository.getRecipeMostEasyToPrepare();
        System.out.println(recipeMostEasilyPrepared);
    }
}