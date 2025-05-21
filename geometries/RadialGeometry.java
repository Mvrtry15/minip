package geometries;

/**
 * Abstract class representing radial geometric shapes.
 * Implements the Geometry interface.
 *
 * @author Raphael
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * The radius of the radial geometry.
     */
    protected final double radius;

    /**
     * The square of the radius (precomputed for optimization).
     */
    protected final double radiusSquared;

    /**
     * Constructs a RadialGeometry with the given radius.
     *
     * @param radius the radius of the shape
     * @throws IllegalArgumentException if radius is not positive
     */
    public RadialGeometry(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
        this.radiusSquared = radius * radius;
    }
}