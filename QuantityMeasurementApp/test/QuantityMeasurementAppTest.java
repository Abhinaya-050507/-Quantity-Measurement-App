import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testEquality_KilogramToGram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }



    @Test
    void testConversion_KgToGram() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight result = w.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKg() {
        QuantityWeight w = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight result = w.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_SameUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_WithTargetUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    void testZeroValue() {
        QuantityWeight w1 = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testNegativeValues() {
        QuantityWeight w1 = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-1000.0, WeightUnit.GRAM);

        assertTrue(w1.equals(w2));
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testNaNValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    void testWeightVsLength_NotEqual() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityLength l = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(w.equals(l));
    }
}