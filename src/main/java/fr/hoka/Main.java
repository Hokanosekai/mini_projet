package fr.hoka;

import fr.hoka.models.Recipe;
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

        System.out.println("------------------- RECIPES -------------------");

        System.out.println("\n------------------- EXERCICE 1 -------------------");
        List<String> recipeTitles = recipeRepository.getRecipeTitles();
        recipeTitles.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 2 -------------------");
        Integer totalAmountOfEggs = recipeRepository.getTotalAmountOfEggs();
        System.out.println(totalAmountOfEggs);

        System.out.println("\n------------------- EXERCICE 3 -------------------");
        List<Recipe> recipesWithOliveOil = recipeRepository.getRecipesWithOliveOil();
        recipesWithOliveOil.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 4 -------------------");
        Map<Recipe, Integer> eggsPerRecipe = recipeRepository.getEggsPerRecipe();
        eggsPerRecipe.forEach((recipe, eggs) -> System.out.println(recipe + " : " + eggs));

        System.out.println("\n------------------- EXERCICE 5 -------------------");
        List<Recipe> recipesWithLessThan500Calories = recipeRepository.getRecipesWithLessThan500Calories();
        recipesWithLessThan500Calories.forEach(System.out::println);

        System.out.println("\n------------------- EXERCICE 6 -------------------");


        System.out.println("\n------------------- EXERCICE 7 -------------------");


        System.out.println("\n------------------- EXERCICE 8 -------------------");


        System.out.println("\n------------------- EXERCICE 9 -------------------");


        System.out.println("\n------------------- EXERCICE 10 -------------------");


        System.out.println("\n------------------- EXERCICE 11 -------------------");


        System.out.println("\n------------------- EXERCICE 12 -------------------");


        System.out.println("\n------------------- EXERCICE 13 -------------------");


        System.out.println("\n------------------- EXERCICE 14 -------------------");


        System.out.println("\n------------------- EXERCICE 15 -------------------");
    }
}