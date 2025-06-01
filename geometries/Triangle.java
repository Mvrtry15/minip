package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Represents a triangle in 3D space.
 * Inherits from Polygon.
 *
 * @author Raphael
 */
public class Triangle extends Polygon {
    /**
     * Constructs a triangle using three vertices.
     *
     * @param p1 the first vertex
     * @param p2 the second vertex
     * @param p3 the third vertex
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}