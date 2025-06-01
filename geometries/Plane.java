package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a plane in 3D space.
 * Each plane is defined by a point and a normal vector.
 *
 * @author Raphael
 */
public class Plane extends Geometry {
    /**
     * A point on the plane.
     */
    private final Point point;

    /**
     * A vector perpendicular (normal) to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane using three points.
     * <p>
     * The plane is defined by the first point and two vectors formed by subtracting the first point
     * from the second and third points respectively. The cross product of these vectors gives the
     * normal vector to the plane, which is then normalized.
     *
     * @param p1 the first point (must not be null)
     * @param p2 the second point (must not be null)
     * @param p3 the third point (must not be null)
     * @throws IllegalArgumentException when the points are collinear or coincide
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.point = p1;
        this.normal = p2.subtract(p1).crossProduct(p3.subtract(p1)).normalize();
    }

    /**
     * Constructs a plane using a point and a normal vector.
     * The normal vector is normalized before storing.
     *
     * @param point  the reference point on the plane (must not be null)
     * @param normal the normal vector to the plane (must not be null)
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @param point a reference point (not used in this implementation)
     * @return the normalized normal vector of the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

}
