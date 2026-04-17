import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ===== YARD TESTS =====
    @Test
    void testEquality_YardToYard_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                1.0,
                QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                2.0,
                QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                3.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(3.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                1.0,
                QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                36.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.compare(36.0,
                QuantityMeasurementApp.LengthUnit.INCH,
                1.0,
                QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                2.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    // ===== CENTIMETER TESTS =====
    @Test
    void testEquality_CentimeterToCentimeter_SameValue() {
        assertTrue(QuantityMeasurementApp.compare(2.0,
                QuantityMeasurementApp.LengthUnit.CENTIMETER,
                2.0,
                QuantityMeasurementApp.LengthUnit.CENTIMETER));
    }

    @Test
    void testEquality_CentimeterToFeet_NonEquivalentValue() {
        assertFalse(QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.CENTIMETER,
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET));
    }

    // ===== TRANSITIVE PROPERTY =====
    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        boolean a = QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                3.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        boolean b = QuantityMeasurementApp.compare(3.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                36.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        boolean c = QuantityMeasurementApp.compare(1.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                36.0,
                QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(a && b && c);
    }

    // ===== EDGE CASES =====
    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength q =
                new QuantityMeasurementApp.QuantityLength(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertFalse(q.equals(null));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityMeasurementApp.QuantityLength(1.0, null);
        });
    }

    // ===== COMPLEX SCENARIO =====
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        assertTrue(QuantityMeasurementApp.compare(2.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                6.0,
                QuantityMeasurementApp.LengthUnit.FEET));

        assertTrue(QuantityMeasurementApp.compare(2.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                72.0,
                QuantityMeasurementApp.LengthUnit.INCH));
    }
}