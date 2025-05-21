package unittests.geometries;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     * Tests the constructor that creates a plane from three points.
     * Test method for {@link geometries.Plane#Plane(Point, Vector)}.
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
     * Test method for {@link Plane#getNormal(Point)}.
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
}