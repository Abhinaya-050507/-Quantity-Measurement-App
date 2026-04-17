public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    public static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {
            // Same reference check (Reflexive)
            if (this == obj) return true;

            // Null and type check
            if (obj == null || getClass() != obj.getClass()) return false;

            // Type casting
            Feet feet = (Feet) obj;

            // Compare double values safely
            return Double.compare(feet.value, this.value) == 0;
        }
    }

    // Main method to test manually
    public static void main(String[] args) {
        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(1.0);

        boolean result = value1.equals(value2);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}