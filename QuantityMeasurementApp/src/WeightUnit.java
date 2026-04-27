public enum WeightUnit {

    KILOGRAM(1.0),      // Base unit
    GRAM(0.001),        // 1 g = 0.001 kg
    POUND(0.453592);    // 1 lb ≈ 0.453592 kg

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    // Getter (optional but useful for testing/debugging)
    public double getConversionFactor() {
        return conversionFactor;
    }

    // ===== Convert TO base unit (kg) =====
    public double convertToBaseUnit(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value: must be finite");
        }
        return value * conversionFactor;
    }

    // ===== Convert FROM base unit (kg) =====
    public double convertFromBaseUnit(double baseValue) {
        if (!Double.isFinite(baseValue)) {
            throw new IllegalArgumentException("Invalid base value: must be finite");
        }
        return baseValue / conversionFactor;
    }
}