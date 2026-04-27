import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConversion_FeetToInches() {
        QuantityLength length = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = length.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testEquality_CrossUnits() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }

    @Test
    void testAddition_ImplicitUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitUnit_Feet() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitUnit_Inches() {
        QuantityLength result = QuantityLength.add(
                1.0, LengthUnit.FEET,
                12.0, LengthUnit.INCHES,
                LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitUnit_Yards() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b, LengthUnit.YARDS);

        assertEquals(0.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_WithZero() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b, LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = a.add(b, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }
    @Test
    void testNullUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(1.0, null));
    }

    @Test
    void testInvalidValue_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}