package fr.hoka.enums;

public enum UnitsKinds {
    POUND       ("pound"),
    JAR         ("jar"),
    CUP         ("cup"),
    TEASPOON    ("teaspoon"),
    OUNCE       ("ounce");

    private final String unit;

    UnitsKinds(String _unit) {
        this.unit = _unit;
    }

    public String getUnit() {
        return this.unit;
    }

    public static UnitsKinds fromString(String _unit) {
        for (UnitsKinds unit : UnitsKinds.values()) {
            if (unit.unit.equalsIgnoreCase(_unit)) {
                return unit;
            }
        }
        return null;
    }
}
