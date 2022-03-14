package net.jfdf.jfdf.values;

import java.util.Locale;

public class Location implements ILocation {

    private final float x;
    private final float y;
    private final float z;

    private final double yaw;
    private final double pitch;

    private boolean isBlock = true;

    public Location(float x, float y) {
        this(x, y, 0.0f);
    }

    public Location(float x, float y, float z) {
        this(x, y, z, 0.0d, 0.0d);
    }

    public Location(float x, float y, float z, double yaw, double pitch) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Location asBlock(boolean asBlock) {
        this.isBlock = asBlock;

        return this;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                ", isBlock=" + isBlock +
                '}';
    }

    @Override
    public String asJSON() {
        return "{\"id\":\"loc\",\"data\":{"
                + "\"isBlock\":" + (isBlock ? "true" : "false")
                + ",\"loc\":{\"x\":" + String.format(Locale.US, "%.0f", x)
                + ",\"y\":" + String.format(Locale.US, "%.0f", y)
                + ",\"z\":" + String.format(Locale.US, "%.0f", z)
                + ",\"yaw\":" + String.format(Locale.US, "%.0f", yaw)
                + ",\"pitch\":" + String.format(Locale.US, "%.0f", pitch) + "}}}";
    }
}