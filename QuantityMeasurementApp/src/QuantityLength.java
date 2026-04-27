
public class QuantityLength {

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

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        return new QuantityLength(result, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Other operand cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumBase =
                this.unit.convertToBaseUnit(this.value) +
                        other.unit.convertToBaseUnit(other.value);

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    public static QuantityLength add(double v1, LengthUnit u1,
                                     double v2, LengthUnit u2,
                                     LengthUnit targetUnit) {

        validate(v1, u1);
        validate(v2, u2);

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumBase =
                u1.convertToBaseUnit(v1) +
                        u2.convertToBaseUnit(v2);

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < 1e-6;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }

    private static void validate(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }
}