package fr.hoka.models;

import java.util.List;

public class ComplexIngredient extends Ingredient {
    private List<Ingredient> ingredients;

    private List<Step> steps;

    public ComplexIngredient(String _name, List<Ingredient> _ingredients, List<Step> _steps) {
        super(_name);
        this.ingredients = _ingredients;
        this.steps       = _steps;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public static class Builder {
        private String name;
        private List<Ingredient> ingredients;
        private List<Step> steps;

        public Builder setName(String _name) {
            this.name = _name;
            return this;
        }

        public Builder setIngredients(List<Ingredient> _ingredients) {
            this.ingredients = _ingredients;
            return this;
        }

        public Builder setSteps(List<Step> _steps) {
            this.steps = _steps;
            return this;
        }

        public ComplexIngredient build() {
            return new ComplexIngredient(this.name, this.ingredients, this.steps);
        }
    }

    @Override
    public String toString() {
        return "ComplexIngredient{" +
                "\n\tname='" + this.getName() + '\'' +
                "\n\t, ingredients=" + this.ingredients +
                "\n\t, steps=" + this.steps +
                "\n}";
    }
}
