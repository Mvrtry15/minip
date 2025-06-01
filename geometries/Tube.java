package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Represents a tube in 3D space.
 *
 * @author Raphael
 */
public class Tube extends RadialGeometry {
    /**
     * The central axis ray of the tube.
     */
    protected final Ray axisRay;

    /**
     * Constructs a tube with a central axis ray and radius.
     *
     * @param axisRay the central axis ray of the tube (must not be null)
     * @param radius  the radius of the tube (must be positive)
     * @throws IllegalArgumentException if radius is not positive or axisRay is null
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        if (axisRay == null)
            throw new IllegalArgumentException("Axis ray cannot be null");
        this.axisRay = axisRay;
    }

    /**
     * Returns the normal vector to the tube at the given point.
     *
     * @param point the point on the tube's surface (must not be null)
     * @return the normalized normal vector at the given point
     * @throws IllegalArgumentException if point is null
     */
    @Override
    public Vector getNormal(Point point) {
        if (point == null)
            throw new IllegalArgumentException("Point cannot be null");

        Vector u = point.subtract(this.axisRay.origin());
        double t = u.dotProduct(axisRay.direction());
        Point o = isZero(t) ? axisRay.origin() : axisRay.origin().add(axisRay.direction().scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public java.util.List<primitives.Point> findIntersections(Ray ray) {
        return null;
    }
}