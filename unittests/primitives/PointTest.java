package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link primitives.Point} class.
 * Provides tests for operations on 3D points such as addition, subtraction, and distance calculations.
 * Covers equivalence partitions and boundary value analysis.
 *
 * @author Raphael
 */
class PointTest {

    /**
     * Default constructor with Javadoc comment.
     */
    PointTest() {
    }

    /**
     * accuracy checker
     */
    private static final double DELTA = 0.000001;

    // ============ Equivalence Partitions Tests ==============

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     * Tests the addition of a vector to a point, including scenarios
     * with positive and negative values, covering equivalence partitions.
     */
    @Test
    void testAdd() {
        // TC01: Basic addition
        assertEquals(new Point(5, 0, 8),
                new Point(1, 2, 3).add(new Vector(4, -2, 5)),
                "Point.add() basic addition failed");

        // TC02: Addition with negative values
        assertEquals(new Point(-1, -2, -3),
                new Point(0, 0, 0).add(new Vector(-1, -2, -3)),
                "Point.add() with negative values failed");
    }

    /**
     * Test method for {@link Point#subtract(Point)}.
     * Verifies the subtraction of one point from another to produce a vector.
     * Tests include basic subtraction and scenarios with negative results.
     */
    @Test
    void testSubtract() {
        // TC03: Basic subtraction
        assertEquals(new Vector(2, 3, 4),
                new Point(3, 4, 5).subtract(new Point(1, 1, 1)),
                "Point.subtract() basic subtraction failed");

        // TC04: Subtraction with negative results
        assertEquals(new Vector(-1, -2, -3),
                new Point(0, 0, 0).subtract(new Point(1, 2, 3)),
                "Point.subtract() with negative results failed");
    }

    // =============== Boundary Values Tests ==================

    /**
     * Test method for {@link Point#subtract(Point)}.
     * Ensures that subtracting identical points throws an IllegalArgumentException.
     * Tests boundary behavior for a point attempting subtraction with itself.
     */
    @Test
    void testSubtractSamePoint() {
        // TC11: Subtracting identical points should throw exception
        assertThrows(IllegalArgumentException.class,
                () -> new Point(1, 2, 3).subtract(new Point(1, 2, 3)),
                "Subtracting identical points should throw exception");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     * Validates the squared distance between two points, including the special case
     * where the distance to itself is zero.
     */
    @Test
    void testDistanceSquared() {
        // TC12: Distance squared to itself should be zero
        assertEquals(0,
                new Point(1, 2, 3).distanceSquared(new Point(1, 2, 3)),
                DELTA,
                "Distance squared to itself should be zero");
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     * Gathers scenarios to validate the calculated distance between two points,
     * especially verifying the distance to itself as zero.
     */
    @Test
    void testDistance() {
        // TC13: Distance to itself should be zero
        assertEquals(0,
                new Point(0, 0, 0).distance(new Point(0, 0, 0)),
                DELTA,
                "Distance to itself should be zero");
    }
}