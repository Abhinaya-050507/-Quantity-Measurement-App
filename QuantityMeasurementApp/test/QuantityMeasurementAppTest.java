import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(
                new QuantityMeasurementApp.QuantityLength(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET),
                a.add(b)
        );
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = a.add(b);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var a = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);
        var b = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = a.add(b);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_Commutativity() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    void testAddition_WithZero() {
        var a = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(0.0,
                QuantityMeasurementApp.LengthUnit.INCHES);

        var result = a.add(b);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        var a = new QuantityMeasurementApp.QuantityLength(5.0,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(-2.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = a.add(b);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Null_Throws() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void testAddition_LargeValues() {
        var a = new QuantityMeasurementApp.QuantityLength(1e6,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(1e6,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = a.add(b);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SmallValues() {
        var a = new QuantityMeasurementApp.QuantityLength(0.001,
                QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(0.002,
                QuantityMeasurementApp.LengthUnit.FEET);

        var result = a.add(b);

        assertEquals(0.003, result.getValue(), EPSILON);
    }
}