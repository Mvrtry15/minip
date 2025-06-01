package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Plane} class.
 * Tests all public methods and constructors of the Plane class.
 *
 * @author Raphael
 */
class PlaneTest {

    /**
     * Explicit constructor with Javadoc comment to satisfy documentation requirements.
     */
    PlaneTest() {
    }

    /**
     * Tolerance value for floating-point comparisons
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the constructor that creates a plane from three points.
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Vector)}.
     * Tests the constructor that creates a plane from a point and normal vector.
     */
    @Test
    void testPlaneConstructor() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(2, 4, 5)),
                "ERROR: failed to create a plane");

        // TC02: test to see that the constructor works correctly
        assertDoesNotThrow(() -> new Plane(new Point(1, 2, 3), new Vector(1, 2, 3)),
                "ERROR: failed to create a plane");

        // =============== Boundary Values Tests ==================

        // TC10: test to see that plane cannot be created with 3 identical points
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(1, 2, 3)),
                "ERROR: plane cannot be created with 3 identical points");

        // TC11: test to see that all three points must be different
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(2, 4, 6)),
                "ERROR: all three points must be different");

        // TC12: test to see that all three points cannot be on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6, 9)),
                "ERROR: all three points cannot be on the same line");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     * Tests the normal vector calculation at a point on the plane.
     */
    @Test
    void testGetNormal() {
        // Test points defining the plane
        Point p0 = new Point(0, 0, 1);
        Point p1 = new Point(1, 0, 1);
        Point p2 = new Point(0, 1, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal calculation at a point on the plane
        Plane plane = new Plane(p0, p1, p2);
        Vector normal = plane.getNormal(new Point(0, 0, 1));

        // Verify normal is unit length
        assertEquals(1, normal.length(), DELTA,
                "Normal vector should be unit length");

        // Vectors in the plane for verification
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);

        // Verify orthogonality to plane vectors
        assertEquals(0, normal.dotProduct(v1), DELTA,
                "Normal should be orthogonal to first plane vector");
        assertEquals(0, normal.dotProduct(v2), DELTA,
                "Normal should be orthogonal to second plane vector");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     * Tests the intersection of a ray with the plane.
     */
    @Test
    void testfindIntersections() {
        Plane plane = new Plane(new Point(1, 1, 1), new Vector(0, 0, 1));
        Point p001 = new Point(0, 0, 1);

        Vector v0m11 = new Vector(0, -1, 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: A ray that starts outside the plane, is not parallel to the plane,
        // not orthogonal to the plane, and intersects the plane in 1 point.
        var result = plane.findIntersections(new Ray(new Point(0, 1, 0), v0m11));
        assertEquals(1, result.size(), "ERROR: Ray should intersect the plane in 1 point");
        assertEquals(List.of(p001), result, "ERROR: Ray's line should intersects the plane");

        // TC01: A ray that starts outside the plane, is not parallel to the plane,
        // not orthogonal to the plane, and intersects the plane in 0 points.
        result = plane.findIntersections(new Ray(new Point(0, 1, 2), v0m11));
        assertEquals(0, result.size(), "ERROR: Ray should NOT intersect the plane at all");
        assertNull(result, "ERROR: Ray's line is NOT out of plane");

        // =============== Boundary Values Tests ==================

        // **** Group 1: Ray's line is parallel to the plane
        Vector vm1m10 = new Vector(-1, -1, 0);

        // TC11: A ray that starts on the plane, who is parallel to the plane. (no intersection)
        result = plane.findIntersections(new Ray(p001, vm1m10));
        assertNull(result, "ERROR: Ray's line is Contained in the plane, should not intersect");

        // TC12: A ray that starts outside the plane, who is parallel to the plane. (no intersection)
        result = plane.findIntersections(new Ray(new Point(0, 0, 0), vm1m10));
        assertNull(result, "ERROR: Ray's line is parallel to the plane, should not intersect");

        // **** Group 2: Ray's line is orthogonal to the plane
        Vector v001 = new Vector(0, 0, 1);

        // TC21: Ray's line starts before the plane and is orthogonal to the plane and intersects the plane (1 point)
        result = plane.findIntersections(new Ray(new Point(0, 0, 0), v001));
        assertEquals(1, result.size(), "ERROR: Ray should intersect the plane in 1 point");
        assertEquals(List.of(p001), result, "ERROR: Ray's line should intersects the plane at the point (0, 0, 1)");

        // TC22: Ray's line starts on the plane and is orthogonal to the plane (0 point)
        result = plane.findIntersections(new Ray(p001, v001));
        assertNull(result, "ERROR: Ray's starting point is Contained in the plane, and is orthogonal to the plane, should not intersect");

        // TC23: Ray's line starts after the plane and is orthogonal to the plane  (0 point)
        result = plane.findIntersections(new Ray(new Point(0, 0, 2), v001));
        assertNull(result, "ERROR: Ray's starting point is outside of the plane, and is orthogonal to the plane, should not intersect");

        // **** Group 3: Ray's line is NOT orthogonal to the plane

        // TC31: Ray's line starts on the point of the plane
        result = plane.findIntersections(new Ray(new Point(1, 1, 1), v0m11));
        assertNull(result, "ERROR: Ray's line starts on the original point of the plane and intersects the plane (0 point)");

        // TC32: Ray's line starts on the plane and intersects the plane (0 point)
        result = plane.findIntersections(new Ray(p001, v0m11));
        assertNull(result, "ERROR: Ray's line starts on the plane and intersects the plane (0 point)");
    }
}