package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * Represents a cylinder in 3D space.
 * A cylinder is defined by a central axis ray, a radius, and a height.
 * Extends {@link Tube}.
 *
 * @author Raphael
 */
public class Cylinder extends Tube {
    /**
     * The height of the cylinder along its axis.
     */
    private final double height;

    /**
     * Constructs a cylinder with a central axis ray, radius, and height.
     *
     * @param axisRay the central axis ray of the cylinder (must not be null)
     * @param radius  the radius of the cylinder (must be positive)
     * @param height  the height of the cylinder (must be positive)
     * @throws IllegalArgumentException if axisRay is null or height is not positive
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.height = height;
    }

    /**
     * Returns the normal vector to the cylinder at the given point.
     * <p>
     * The logic distinguishes:
     * <ul>
     *     <li>Bottom base (t ≈ 0) → opposite axis direction</li>
     *     <li>Top base (t ≈ height) → axis direction</li>
     *     <li>Side surface → perpendicular to axis</li>
     * </ul>
     *
     * @param point the point on the cylinder's surface (must not be null)
     * @return the normalized normal vector at the given point
     * @throws IllegalArgumentException if point is null
     */
    @Override
    public Vector getNormal(Point point) {
        if (point == null)
            throw new IllegalArgumentException("Point cannot be null");

        Vector v = point.subtract(axisRay.origin());
        double t = axisRay.direction().dotProduct(v);

        if (Util.isZero(t)) {
            return axisRay.direction().scale(-1);
        }

        if (Util.isZero(t - height)) {
            return axisRay.direction();
        }

        Point closestPoint = axisRay.origin().add(axisRay.direction().scale(t));
        return point.subtract(closestPoint).normalize();
    }
}
