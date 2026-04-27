public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // ===== LENGTH DEMO =====
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Length Conversion:");
        System.out.println(l1 + " -> " + l1.convertTo(LengthUnit.INCHES));

        System.out.println("\nLength Addition:");
        System.out.println(l1 + " + " + l2 + " = " + l1.add(l2));
        System.out.println(l1 + " + " + l2 + " (in YARDS) = " + l1.add(l2, LengthUnit.YARDS));

        System.out.println("\nLength Equality:");
        System.out.println(l1 + " equals " + l2 + " ? " + l1.equals(l2));

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("\nWeight Conversion:");
        System.out.println(w1 + " -> " + w1.convertTo(WeightUnit.GRAM));

        System.out.println("\nWeight Addition:");
        System.out.println(w1 + " + " + w2 + " = " + w1.add(w2));
        System.out.println(w1 + " + " + w2 + " (in POUND) = " + w1.add(w2, WeightUnit.POUND));

        System.out.println("\nWeight Equality:");
        System.out.println(w1 + " equals " + w2 + " ? " + w1.equals(w2));
    }
}