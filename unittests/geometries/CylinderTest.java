package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link Cylinder} class.
 * Covers normal calculations on sides and bases.
 * <p>
 * Author: Raphael
 */
class CylinderTest {
    /**
     * Explicit empty default constructor to satisfy Javadoc generator.
     */
    CylinderTest() {
    }

    /**
     * Ray representing the axis of the cylinder.
     */
    private final Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

    /**
     * Cylinder instance for testing.
     */
    private final Cylinder cylinder = new Cylinder(axis, 1, 5);

    /**
     * Test method for {@link Cylinder#getNormal(Point)}.
     * Verifies that the normal vector returned is correct for different points on the surface of the cylinder,
     * including side, base, and edge cases.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Side surface
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(1, 0, 2)),
                "TC01: Incorrect normal on side surface");

        // TC02: Diagonal side
        Vector expected2 = new Vector(1, 1, 0).normalize();
        assertEquals(expected2, cylinder.getNormal(new Point(1, 1, 3)),
                "TC02: Incorrect normal on diagonal side");

        // =============== Boundary Values Tests ==================

        // TC11: Bottom base
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(0.5, 0, 0)),
                "TC11: Incorrect normal on bottom base");

        // TC12: Top base
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(0, -0.5, 5)),
                "TC12: Incorrect normal on top base");

        // TC13: Edge between side and bottom
        assertEquals(new Vector(0, 0, -1), cylinder.getNormal(new Point(1, 0, 0)),
                "TC13: Incorrect normal at bottom edge");

        // TC14: Edge between side and top
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(1, 0, 5)),
                "TC14: Incorrect normal at top edge");

        // TC15: Null point (should throw)
        assertThrows(IllegalArgumentException.class,
                () -> cylinder.getNormal(null), "TC15: Expected exception for null point");
    }
}
