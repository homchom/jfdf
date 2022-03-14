package net.jfdf.jfdf.values;

import java.util.Locale;

public class Vector implements IVector {
    private final double x;
    private final double y;
    private final double z;

    public Vector(double x, double y) {
        this(x, y, 0.0d);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public String asJSON() {
        return "{\"id\":\"vec\",\"data\":{\"x\":" + String.format(Locale.US, "%.0f", x)
                + ",\"y\":" + String.format(Locale.US, "%.0f", y)
                + ",\"z\":" + String.format(Locale.US, "%.0f", z) + "}}";
    }
}
