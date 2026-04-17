import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== SAME UNIT TESTS =====
    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                1.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    // ===== CROSS UNIT TESTS =====
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                12.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(12.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    // ===== DIFFERENT VALUES =====
    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                2.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                2.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    // ===== EDGE CASES =====
    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(1.0, null);
        });
    }
}