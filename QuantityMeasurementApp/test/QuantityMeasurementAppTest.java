import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(72.0,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        assertEquals(5.0,
                QuantityMeasurementApp.convert(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        double original = 5.0;

        double converted = QuantityMeasurementApp.convert(
                original,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCHES
        );

        double back = QuantityMeasurementApp.convert(
                converted,
                QuantityMeasurementApp.LengthUnit.INCHES,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(original, back, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(
                        1.0,
                        null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES));
    }

    @Test
    void testConversion_Infinite_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(
                        Double.POSITIVE_INFINITY,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES));
    }
}