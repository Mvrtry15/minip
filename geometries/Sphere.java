package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a sphere in 3D space.
 * A sphere is defined by its center point and a radius.
 *
 * @author Raphael
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a sphere with a center point and radius.
     *
     * @param center the center point of the sphere (must not be null)
     * @param radius the radius of the sphere (must be positive)
     * @throws IllegalArgumentException if radius is not positive
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Returns the normal vector to the sphere at the given point.
     * <p>
     * The normal is the vector from the center of the sphere to the given point,
     * normalized to unit length.
     *
     * @param point the point on the sphere's surface (must not be null)
     * @return the normalized normal vector at the given point
     * @throws IllegalArgumentException if the point is at the center
     */
    @Override
    public Vector getNormal(Point point) {
        Vector normal = point.subtract(center);
        return normal.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}