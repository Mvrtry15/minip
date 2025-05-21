package unittests.geometries;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Sphere} class.
 * Tests the functionality of the Sphere class including normal vector calculation.
 *
 * @author Raphael
 */
class SphereTest {

    /**
     * Default constructor required for Javadoc.
     */
    SphereTest() {
    }

    /**
     * Tolerance value for floating-point comparisons
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     * Tests normal vector calculation for points on the sphere's surface.
     */
    @Test
    void testGetNormal() {
        // Create unit sphere centered at origin
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal calculation for point not on any axis
        Vector expected = new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3));
        Point testPoint = new Point(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3));
        Vector actual = sphere.getNormal(testPoint);

        assertEquals(expected, actual, "Normal vector should point radially outward from center");
    }

    /**
     * Test method for {@link Sphere#getNormal(Point)}.
     * Tests normal vector calculation for points on each axis.
     */
    @Test
    void testGetNormalOnAxes() {
        // Create unit sphere centered at origin
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);

        // =============== Boundary Values Tests ==================
        // TC11: Test normal on positive X-axis
        assertEquals(new Vector(1, 0, 0), sphere.getNormal(new Point(1, 0, 0)),
                "Normal on +X axis should point along X-axis");

        // TC12: Test normal on negative X-axis
        assertEquals(new Vector(-1, 0, 0), sphere.getNormal(new Point(-1, 0, 0)),
                "Normal on -X axis should point along negative X-axis");

        // TC13: Test normal on positive Y-axis
        assertEquals(new Vector(0, 1, 0), sphere.getNormal(new Point(0, 1, 0)),
                "Normal on +Y axis should point along Y-axis");

        // TC14: Test normal on negative Y-axis
        assertEquals(new Vector(0, -1, 0), sphere.getNormal(new Point(0, -1, 0)),
                "Normal on -Y axis should point along negative Y-axis");

        // TC15: Test normal on positive Z-axis
        assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 1)),
                "Normal on +Z axis should point along Z-axis");

        // TC16: Test normal on negative Z-axis
        assertEquals(new Vector(0, 0, -1), sphere.getNormal(new Point(0, 0, -1)),
                "Normal on -Z axis should point along negative Z-axis");
    }

    /**
     * Test method for {@link Sphere#getNormal(Point)}.
     * Tests behavior when point is at sphere's center.
     */
    @Test
    void testGetNormalAtCenter() {
        // Create unit sphere centered at origin
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);

        // =============== Boundary Values Tests ==================
        // TC21: Test behavior when point is at center (should throw exception)
        assertThrows(IllegalArgumentException.class,
                () -> sphere.getNormal(new Point(0, 0, 0)),
                "Should throw exception when point is at sphere's center");
    }
}