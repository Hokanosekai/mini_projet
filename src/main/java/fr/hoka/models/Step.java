package fr.hoka.models;

public class Step {
    private String description;

    public Step(String _description) {
        this.description = _description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String _description) {
        this.description = _description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
