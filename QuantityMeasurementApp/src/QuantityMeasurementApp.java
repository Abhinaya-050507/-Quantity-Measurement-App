public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Conversion:");
        System.out.println(a.convertTo(LengthUnit.INCHES));

        System.out.println("\nAddition (Implicit Unit):");
        System.out.println(a.add(b));

        System.out.println("\nAddition (Explicit Unit):");
        System.out.println(a.add(b, LengthUnit.INCHES));


        System.out.println(a.add(b, LengthUnit.YARDS));

        System.out.println("\nEquality:");
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength d = new QuantityLength(1.0, LengthUnit.YARDS);

        System.out.println(c.equals(d));

        System.out.println("\nDirect Unit Conversion:");
        System.out.println(LengthUnit.INCHES.convertToBaseUnit(12.0));
        System.out.println(LengthUnit.YARDS.convertFromBaseUnit(3.0));
    }
}