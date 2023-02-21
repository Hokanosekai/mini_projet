package fr.hoka.utils;

import fr.hoka.models.*;
import fr.hoka.repositories.RecipeRepository;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Document document;

    public Parser(
            String _file
    ) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        File file                      = new File("src/main/resources/" + _file);
        DocumentBuilder builder        = factory.newDocumentBuilder();

        try {
            this.document = builder.parse(file);
            this.document.getDocumentElement().normalize();
        } catch (SAXException | IOException e) {
            System.out.println("Error while parsing the XML file");
            e.printStackTrace();
            throw e;
        }
    }

    public RecipeRepository parse() throws IOException {
        NodeList nodeList                 = this.document.getElementsByTagName("rcp:recipe");
        RecipeRepository recipeRepository = new RecipeRepository();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element node                 = (Element) nodeList.item(i);

            NodeList ingredientsNodes    = node.getElementsByTagName("rcp:ingredient");
            Element titleNode            = (Element) node.getElementsByTagName("rcp:title").item(0);
            Element dateNode             = (Element) node.getElementsByTagName("rcp:date").item(0);
            Element nutritionNode        = (Element) node.getElementsByTagName("rcp:nutrition").item(0);
            Element commentNode          = (Element) node.getElementsByTagName("rcp:comment").item(0);
            Element preparationNode      = (Element) node.getElementsByTagName("rcp:preparation").item(0);

            List<Ingredient> ingredients = this.parseIngredients(ingredientsNodes);
            Nutrition nutrition          = this.parseNutrition(nutritionNode);
            List<Step> steps             = this.parseSteps(preparationNode);

            String id                    = node.getAttribute("id");
            String title                 = titleNode.getTextContent();
            String date                  = dateNode.getTextContent();
            String comment               = commentNode != null
                    ? commentNode.getTextContent()
                    : null;

            Recipe recipe = new Recipe.Builder()
                    .setId(id)
                    .setTitle(title)
                    .setComment(comment)
                    .setDate(date)
                    .setIngredients(ingredients)
                    .setNutrition(nutrition)
                    .setSteps(steps)
                    .build();

            recipeRepository.addRecipe(recipe);
        }

        return recipeRepository;
    }

    private List<Ingredient> parseIngredients(NodeList _ingredientsNodes) {
        List<Ingredient> ingredients = new ArrayList<>();

        for (int i = 0; i < _ingredientsNodes.getLength(); i++) {
            Element childNode            = (Element) _ingredientsNodes.item(i);
            String name                  = childNode.getAttribute("name");
            String amount                = childNode.getAttribute("amount");
            String unit                  = childNode.getAttribute("unit");

            NodeList childIngredients    = childNode.getElementsByTagName("rcp:ingredient");

            Ingredient ingredient = amount.isEmpty() && unit.isEmpty()
                    ? new ComplexIngredient.Builder()
                            .setName(name)
                            .setIngredients(this.parseIngredients(childIngredients))
                            .build()
                    : new SimpleIngredient.Builder()
                            .setName(name)
                            .setAmount(amount)
                            .setUnit(unit)
                            .build();

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    private Nutrition parseNutrition(Element _nutritionNode) {
        String calories = _nutritionNode.getAttribute("calories");
        String fat      = _nutritionNode.getAttribute("fat");
        String protein  = _nutritionNode.getAttribute("protein");
        String carbs    = _nutritionNode.getAttribute("carbs");

        return new Nutrition.Builder()
                .setCalories(calories)
                .setFats(fat)
                .setProteins(protein)
                .setCarbohydrates(carbs)
                .build();
    }

    private List<Step> parseSteps(Element _preparationNode) {
        List<Step> steps     = new ArrayList<>();
        NodeList _stepsNodes = _preparationNode.getElementsByTagName("rcp:step");

        for (int i = 0; i < _stepsNodes.getLength(); i++) {
            Element childNode = (Element) _stepsNodes.item(i);
            String content    = childNode.getTextContent();
            Step step         = new Step(content);

            steps.add(step);
        }

        return steps;
    }
}
