package fr.hoka.models;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    String id;
    String title;
    String comment;
    String date;
    Nutrition nutrition;
    List<Ingredient> ingredients = new ArrayList<>();
    List<Step> steps             = new ArrayList<>();
    public Recipe(String _id, String _title, String _comment, String _date, Nutrition _nutrition, List<Ingredient> _ingredients, List<Step> _steps) {
        this.id          = _id;
        this.title       = _title;
        this.comment     = _comment;
        this.date        = _date;
        this.nutrition   = _nutrition;
        this.ingredients = _ingredients;
        this.steps       = _steps;
    }

    public static class Builder {
        private String id;
        private String title;
        private String comment;
        private String date;
        private Nutrition nutrition;
        private List<Ingredient> ingredients = new ArrayList<>();
        private List<Step> steps             = new ArrayList<>();

        public Builder setId(String _id) {
            this.id = _id;
            return this;
        }

        public Builder setTitle(String _title) {
            this.title = _title;
            return this;
        }

        public Builder setComment(String _comment) {
            this.comment = _comment;
            return this;
        }

        public Builder setDate(String _date) {
            this.date = _date;
            return this;
        }

        public Builder setNutrition(Nutrition _nutrition) {
            this.nutrition = _nutrition;
            return this;
        }

        public Builder setIngredients(List<Ingredient> _ingredients) {
            this.ingredients = _ingredients;
            return this;
        }

        public Builder addIngredient(Ingredient _ingredient) {
            this.ingredients.add(_ingredient);
            return this;
        }

        public Builder addIngredients(List<Ingredient> _ingredients) {
            this.ingredients.addAll(_ingredients);
            return this;
        }

        public Builder setSteps(List<Step> _steps) {
            this.steps = _steps;
            return this;
        }

        public Builder addStep(Step _step) {
            this.steps.add(_step);
            return this;
        }

        public Builder addSteps(List<Step> _steps) {
            this.steps.addAll(_steps);
            return this;
        }

        public Recipe build() {
            return new Recipe(this.id, this.title, this.comment, this.date, this.nutrition, this.ingredients, this.steps);
        }
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getComment() {
        return this.comment;
    }

    public String getDate() {
        return this.date;
    }

    public Nutrition getNutrition() {
        return this.nutrition;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public Integer getCalories() {
        return Integer.valueOf(this.nutrition.getCalories());
    }

    @Override
    public String toString() {
        return this.title + " (" + this.id + ")";
    }
}
