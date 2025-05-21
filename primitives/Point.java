package primitives;

/**
 * Represents a point in 3D space.
 * This class uses a Double3 object to store the coordinates (x, y, z) and provides methods for
 * vector addition, subtraction, and distance calculations.
 *
 * @author Raphael
 */
public class Point {
    /**
     * A constant representing the origin point (0, 0, 0).
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * The coordinates of the point stored as a Double3 object.
     */
    protected final Double3 xyz;

    /**
     * Constructs a point using a Double3 object.
     *
     * @param otherXyz the Double3 object representing the coordinates
     */
    protected Point(Double3 otherXyz) {
        this.xyz = otherXyz;
    }

    /**
     * Constructs a point with the specified x, y, and z coordinates.
     *
     * @param x the X-coordinate value
     * @param y the Y-coordinate value
     * @param z the Z-coordinate value
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Adds the given vector to this point.
     *
     * @param vector the vector to add (must not be null)
     * @return a new point that is the result of translating this point by the vector
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Subtracts the specified point from this point.
     * <p>
     * The result is a vector from the specified point to this point.
     * If the points are identical, a zero vector will be generated, which is not allowed.
     *
     * @param otherPoint the point to subtract (must not be null)
     * @return the vector representing the subtraction result
     * @throws IllegalArgumentException if the result is a zero vector
     */
    public Vector subtract(Point otherPoint) {
        Double3 result = xyz.subtract(otherPoint.xyz);
        if (result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Cannot create zero vector");
        }
        return new Vector(result);
    }

    /**
     * Computes the squared distance between this point and the specified point.
     *
     * @param otherPoint the point to measure the squared distance to (must not be null)
     * @return the squared distance between the two points
     */
    public double distanceSquared(Point otherPoint) {
        double d1 = xyz.d1() - otherPoint.xyz.d1();
        double d2 = xyz.d2() - otherPoint.xyz.d2();
        double d3 = xyz.d3() - otherPoint.xyz.d3();
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    /**
     * Computes the distance between this point and the specified point.
     *
     * @param otherPoint the point to measure the distance to (must not be null)
     * @return the distance between the two points
     */
    public double distance(Point otherPoint) {
        return Math.sqrt(distanceSquared(otherPoint));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Point other && xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "" + xyz;
    }
}