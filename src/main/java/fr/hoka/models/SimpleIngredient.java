package fr.hoka.models;

import fr.hoka.enums.UnitsKinds;

public class SimpleIngredient extends Ingredient {

    private String amount;

    private UnitsKinds unit;

    public SimpleIngredient(String _name, String _amount, UnitsKinds _unit) {
        super(_name);
        this.amount = _amount;
        this.unit   = _unit;
    }

    public String getAmount() {
        return this.amount;
    }

    public UnitsKinds getUnit() {
        return this.unit;
    }

    public static class Builder {
        private String name;
        private String amount;
        private UnitsKinds unit;

        public Builder setName(String _name) {
            this.name = _name;
            return this;
        }

        public Builder setAmount(String _amount) {
            this.amount = _amount;
            return this;
        }

        public Builder setUnit(UnitsKinds _unit) {
            this.unit = _unit;
            return this;
        }

        public Builder setUnit(String _unit) {
            this.unit = UnitsKinds.fromString(_unit);
            return this;
        }

        public SimpleIngredient build() {
            return new SimpleIngredient(this.name, this.amount, this.unit);
        }
    }
}
