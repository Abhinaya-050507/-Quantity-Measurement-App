public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        /**
         * UC6: add → result in THIS unit
         */
        public QuantityLength add(QuantityLength other) {
            return add(this, other, this.unit);
        }

        /**
         * UC7: add → result in EXPLICIT target unit
         */
        public static QuantityLength add(QuantityLength a,
                                         QuantityLength b,
                                         LengthUnit targetUnit) {

            if (a == null || b == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double result = addInTargetUnit(a, b, targetUnit);
            return new QuantityLength(result, targetUnit);
        }

        /**
         * Overloaded raw-value version
         */
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2,
                                         LengthUnit targetUnit) {

            validate(v1, u1);
            validate(v2, u2);

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumBase = (v1 * u1.getFactor()) + (v2 * u2.getFactor());
            double result = sumBase / targetUnit.getFactor();

            return new QuantityLength(result, targetUnit);
        }

        /**
         * 🔑 Private utility method (DRY)
         */
        private static double addInTargetUnit(QuantityLength a,
                                              QuantityLength b,
                                              LengthUnit targetUnit) {

            double baseSum = toBase(a) + toBase(b);
            return baseSum / targetUnit.getFactor();
        }

        private static double toBase(QuantityLength q) {
            return q.value * q.unit.getFactor();
        }

        private static void validate(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            double diff = Math.abs(toBase(this) - toBase(other));
            return diff < 1e-6;
        }
    }

    public static void main(String[] args) {

        var a = new QuantityLength(1.0, LengthUnit.FEET);
        var b = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(QuantityLength.add(a, b, LengthUnit.FEET));   // 2 FEET
        System.out.println(QuantityLength.add(a, b, LengthUnit.INCHES)); // 24 INCHES
        System.out.println(QuantityLength.add(a, b, LengthUnit.YARDS));  // ~0.667 YARDS
    }
}