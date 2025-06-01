package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link geometries.Tube} class.
 * Tests the functionality of the Tube class including normal vector calculation.
 *
 * @author Raphael
 */
class TubeTest {

    /**
     * Default constructor with documentation.
     */
    TubeTest() {
    }

    /**
     * The axis ray for the tube (along Z-axis from origin)
     */
    private final Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

    /**
     * Test tube with radius 1
     */
    private final Tube tube = new Tube(axis, 1);

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     * Tests normal vector calculation for points on the tube's surface.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal for point on X side of tube
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 5)),
                "Normal for point on X side should point radially outward");

        // TC02: Test normal for point on Y side of tube
        assertEquals(new Vector(0, 1, 0), tube.getNormal(new Point(0, 1, 10)),
                "Normal for point on Y side should point radially outward");

        // TC03: Test normal for point on diagonal side of tube
        Vector expectedDiagonal = new Vector(1, 1, 0).normalize();
        assertEquals(expectedDiagonal, tube.getNormal(new Point(1, 1, 7)),
                "Normal for diagonal point should be normalized radial vector");

        // =============== Boundary Values Tests ==================
        // TC11: Test normal at base of tube (z=0)
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 0)),
                "Normal at base point should point radially outward");

        // TC12: Test normal far along tube axis
        assertEquals(new Vector(0, -1, 0), tube.getNormal(new Point(0, -1, 1000)),
                "Normal far along tube should still point radially outward");

        // TC13: Test null point input
        assertThrows(IllegalArgumentException.class,
                () -> tube.getNormal(null),
                "Should throw exception for null point input");
    }
}