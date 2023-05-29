package ase.types;

public enum Unit {
    SECONDS("sec"), MINUTES("min"), HOURS("h");

    private final String unit;

    private Unit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }
}
