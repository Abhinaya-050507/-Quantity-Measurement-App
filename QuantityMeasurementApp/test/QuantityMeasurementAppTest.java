import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_TargetUnit_Feet() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength.add(
                a, b, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnit_Inches() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                12.0, QuantityMeasurementApp.LengthUnit.INCHES,
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_TargetUnit_Yards() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = QuantityMeasurementApp.QuantityLength.add(
                a, b, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_Commutativity_WithTarget() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var r1 = QuantityMeasurementApp.QuantityLength.add(a, b,
                QuantityMeasurementApp.LengthUnit.YARDS);

        var r2 = QuantityMeasurementApp.QuantityLength.add(b, a,
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(r1, r2);
    }

    @Test
    void testAddition_TargetUnit_Null() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.QuantityLength.add(a, a, null));
    }

    @Test
    void testAddition_TargetUnit_WithZero() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                5.0, QuantityMeasurementApp.LengthUnit.FEET,
                0.0, QuantityMeasurementApp.LengthUnit.INCHES,
                QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(1.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_TargetUnit_Negative() {
        var result = QuantityMeasurementApp.QuantityLength.add(
                5.0, QuantityMeasurementApp.LengthUnit.FEET,
                -2.0, QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }
}