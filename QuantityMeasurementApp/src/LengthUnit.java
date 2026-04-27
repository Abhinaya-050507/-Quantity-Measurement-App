
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double factorToFeet;

    LengthUnit(double factorToFeet) {
        this.factorToFeet = factorToFeet;
    }

    public double convertToBaseUnit(double value) {
        validate(value);
        return value * factorToFeet;
    }

    public double convertFromBaseUnit(double baseValue) {
        validate(baseValue);
        return baseValue / factorToFeet;
    }

    public double getConversionFactor() {
        return factorToFeet;
    }

    private void validate(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }
}