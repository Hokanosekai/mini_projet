package fr.hoka.models;

public abstract class Ingredient {
    String name;
    public Ingredient(String _name) {
        this.name     = _name;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
