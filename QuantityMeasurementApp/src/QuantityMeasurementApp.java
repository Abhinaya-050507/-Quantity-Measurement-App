/**
 * QuantityMeasurementApp demonstrating addition and conversion of length units.
 */
public class QuantityMeasurementApp {

    /**
     * Enum representing Length Units (base unit = FEET)
     */
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

    /**
     * Immutable Value Object for Length
     */
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        /**
         * Convert to another unit
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            double converted = convert(value, unit, targetUnit);
            return new QuantityLength(converted, targetUnit);
        }

        /**
         * Instance method for addition
         * Result will be in the unit of THIS object
         */
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Other length cannot be null");
            }

            double sumInBase = toBase(this) + toBase(other);

            double result = fromBase(sumInBase, this.unit);

            return new QuantityLength(result, this.unit);
        }

        /**
         * Static add method (flexible API)
         */
        public static QuantityLength add(QuantityLength a, QuantityLength b) {
            if (a == null || b == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }
            return a.add(b);
        }

        /**
         * Overloaded add using raw values
         */
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2,
                                         LengthUnit targetUnit) {

            validate(v1, u1);
            validate(v2, u2);

            double sumBase = (v1 * u1.getFactor()) + (v2 * u2.getFactor());

            double result = sumBase / targetUnit.getFactor();

            return new QuantityLength(result, targetUnit);
        }

        /**
         * Convert any QuantityLength to base (FEET)
         */
        private static double toBase(QuantityLength q) {
            return q.value * q.unit.getFactor();
        }

        /**
         * Convert from base to target unit
         */
        private static double fromBase(double baseValue, LengthUnit target) {
            return baseValue / target.getFactor();
        }

        /**
         * Reuse UC5 conversion logic
         */
        public static double convert(double value, LengthUnit source, LengthUnit target) {
            validate(value, source);

            double base = value * source.getFactor();
            return base / target.getFactor();
        }

        /**
         * Validation
         */
        private static void validate(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            double thisBase = toBase(this);
            double otherBase = toBase(other);

            return Math.abs(thisBase - otherBase) < 1e-6;
        }
    }

    /**
     * Demo main
     */
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(a.add(b)); // Quantity(2.0, FEET)

        QuantityLength c = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength d = new QuantityLength(1.0, LengthUnit.FEET);

        System.out.println(c.add(d)); // Quantity(24.0, INCHES)

        System.out.println(
                QuantityLength.add(1.0, LengthUnit.YARDS,
                        3.0, LengthUnit.FEET,
                        LengthUnit.YARDS)
        ); // Quantity(2.0, YARDS)
    }
}