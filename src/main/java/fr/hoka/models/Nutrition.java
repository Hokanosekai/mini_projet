package fr.hoka.models;

public class Nutrition {
    private String calories;
    private String carbohydrates;
    private String proteins;
    private String fats;

    public Nutrition(String _calories, String _carbohydrates, String _proteins, String _fats) {
        this.calories      = _calories;
        this.carbohydrates = _carbohydrates;
        this.proteins      = _proteins;
        this.fats          = _fats;
    }

    public static class Builder {
        private String calories;
        private String carbohydrates;
        private String proteins;
        private String fats;

        public Builder setCalories(String _calories) {
            this.calories = _calories;
            return this;
        }

        public Builder setCarbohydrates(String _carbohydrates) {
            this.carbohydrates = _carbohydrates;
            return this;
        }

        public Builder setProteins(String _proteins) {
            this.proteins = _proteins;
            return this;
        }

        public Builder setFats(String _fats) {
            this.fats = _fats;
            return this;
        }

        public Nutrition build() {
            return new Nutrition(this.calories, this.carbohydrates, this.proteins, this.fats);
        }
    }

    public String getCalories() {
        return this.calories;
    }

    public String getCarbohydrates() {
        return this.carbohydrates;
    }

    public String getProteins() {
        return this.proteins;
    }

    public String getFats() {
        return this.fats;
    }
}
