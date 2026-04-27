/**
 * QuantityMeasurementApp provides length conversion functionality.
 * It supports conversion between FEET, INCHES, YARDS, and CENTIMETERS.
 */
public class QuantityMeasurementApp {

    /**
     * Enum for Length Units with conversion factors relative to base unit (FEET)
     */
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084); // 1 cm in feet

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Converts a value from source unit to target unit.
     *
     * @param value value to convert
     * @param source source unit
     * @param target target unit
     * @return converted value
     */
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validateInputs(value, source, target);

        double baseValue = toBaseUnit(value, source);     // Step 1: to FEET
        return fromBaseUnit(baseValue, target);           // Step 2: to target
    }

    /**
     * Converts value to base unit (FEET)
     */
    private static double toBaseUnit(double value, LengthUnit unit) {
        return value * unit.getConversionFactor();
    }

    /**
     * Converts from base unit (FEET) to target unit
     */
    private static double fromBaseUnit(double baseValue, LengthUnit target) {
        return baseValue / target.getConversionFactor();
    }

    /**
     * Input validation
     */
    private static void validateInputs(double value, LengthUnit source, LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
    }

    /**
     * Demonstration method (overloaded version 1)
     */
    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {
        double result = convert(value, from, to);
        System.out.println(value + " " + from + " = " + result + " " + to);
    }

    /**
     * Main method for manual testing
     */
    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);
    }
}