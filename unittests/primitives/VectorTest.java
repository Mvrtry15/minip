package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link primitives.Vector} class.
 * Tests all public methods and constructors of the Vector class.
 */
class VectorTest {

    /**
     * Explicit empty constructor for Javadoc purposes.
     */
    VectorTest() {
    }

    /**
     * Tolerance value for floating-point comparisons
     */
    private static final double DELTA = 0.000001;

    /**
     * Test vector (1,1,1)
     */
    private static final Vector ONE = new Vector(1, 1, 1);
    /**
     * Test vector (1,2,3)
     */
    private static final Vector V1 = new Vector(1, 2, 3);
    /**
     * Test vector (4,5,6)
     */
    private static final Vector V2 = new Vector(4, 5, 6);
    /**
     * Test vector (-1,-1,-3)
     */
    private static final Vector V3 = new Vector(-1, -1, -3);
    /**
     * Test vector with large values (1e10, -1e10, 1e10)
     */
    private static final Vector V4 = new Vector(1e10, -1e10, 1e10);
    /**
     * Test vector with large negative values (-1e10, 1e10+1, -1e10)
     */
    private static final Vector V5 = new Vector(-1e10, 1e10 + 1, -1e10);
    /**
     * Test vector with maximum double values
     */
    private static final Vector V6 = new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    /**
     * Test vector (1,-1,0)
     */
    private static final Vector V7 = new Vector(1, -1, 0);
    /**
     * Test vector with very small values (1e-10, 1e-10, 1e-10)
     */
    private static final Vector V8 = new Vector(1e-10, 1e-10, 1e-10);
    /**
     * Test vector with small negative values
     */
    private static final Vector V9 = new Vector(-1e-10 + 0.000001, -1e-10, -1e-10);
    /**
     * Test vector with mixed small values
     */
    private static final Vector V10 = new Vector(1e-10, 0, -1e-10);
    /**
     * Test vector with mixed small negative values
     */
    private static final Vector V11 = new Vector(-1e-10, 1e-10, 1e-10);

    /**
     * Test method for {@link Vector#Vector(double, double, double)}.
     * Tests the constructor with three double parameters.
     */
    @Test
    void testConstructor3double() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test basic constructor
        Vector v2 = new Vector(1, 2, 3);
        assertEquals(V1, v2, "Vectors should be equal");

        // TC02: Test negative values
        assertDoesNotThrow(() -> new Vector(-1, -2, -3),
                "Failed to create vector with negative values");

        // TC03: Test extreme values
        assertDoesNotThrow(() -> V4,
                "Failed to create vector with extreme values");

        // TC04: Test decimal values
        assertDoesNotThrow(() -> new Vector(0.1, 0.2, 0.3),
                "Failed to create vector with decimal values");

        // =============== Boundary Values Tests ==================
        // TC11: Test zero vector throws exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
                "Should throw exception for zero vector");
    }

    /**
     * Test method for {@link Vector#Vector(Double3)}.
     * Tests the constructor with Double3 parameter.
     */
    @Test
    void testConstructorDouble3() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test basic constructor
        Vector v1 = new Vector(new Double3(1, 2, 3));
        Vector v2 = new Vector(new Double3(1, 2, 3));
        assertEquals(v1, v2, "Vectors should be equal");

        // TC02: Test negative values
        assertDoesNotThrow(() -> new Vector(new Double3(-1, -2, -3)),
                "Failed to create vector with negative values");

        // TC03: Test extreme values
        assertDoesNotThrow(() -> new Vector(new Double3(1e10, -1e10, 1e-10)),
                "Failed to create vector with extreme values");

        // TC04: Test decimal values
        assertDoesNotThrow(() -> new Vector(new Double3(0.1, 0.2, 0.3)),
                "Failed to create vector with decimal values");

        // =============== Boundary Values Tests ==================
        // TC11: Test zero vector throws exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(new Double3(0, 0, 0)),
                "Should throw exception for zero vector");
    }

    /**
     * Test method for {@link Vector#add(Vector)}.
     * Tests vector addition operation.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test adding two positive vectors
        assertEquals(new Vector(5, 7, 9), V1.add(V2),
                "Add operation failed for two positive vectors");

        // TC02: Test adding with negative vector
        assertEquals(new Vector(0, 1, 0), V1.add(V3),
                "Add operation failed for vector and its negative");

        // TC03: Test adding large values
        assertEquals(new Vector(0, 1, 0), V4.add(V5),
                "Add operation failed for vectors with large values");

        // =============== Boundary Values Tests ==================
        // TC11: Test boundary value addition
        assertEquals(new Vector(Double.MAX_VALUE + 1, Double.MAX_VALUE - 1, Double.MAX_VALUE),
                V6.add(V7), "Add operation failed near boundary limits");

        // TC12: Test small values addition
        assertEquals(new Vector(0.000001, 0, 0), V8.add(V9),
                "Add operation failed for extremely small values");

        // TC13: Test near-zero addition
        assertEquals(new Vector(0, 1e-10, 0), V10.add(V11),
                "Add operation failed for values close to zero");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     * Tests vector scaling operation.
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test positive scaling
        assertEquals(new Vector(2, 4, 6), V1.scale(2),
                "Scaling with positive scalar failed");

        // TC02: Test negative scaling
        assertEquals(new Vector(-2, -4, -6), V1.scale(-2),
                "Scaling with negative scalar failed");

        // TC03: Test fractional scaling
        assertEquals(new Vector(0.5, 1, 1.5), V1.scale(0.5),
                "Scaling with fractional scalar failed");

        // =============== Boundary Values Tests ==================
        // TC11: Test large value scaling
        double largeValue = Double.MAX_VALUE / 2;
        assertEquals(new Vector(largeValue, -largeValue, 0.0), V7.scale(largeValue),
                "Scaling near maximum double value failed");

        // TC12: Test small value scaling
        double smallValue = 0.0000001;
        assertEquals(new Vector(smallValue, smallValue, smallValue), ONE.scale(smallValue),
                "Scaling near minimum positive double value failed");

        // TC13: Test unit vector scaling by -1
        assertEquals(new Vector(0, 0, -1), new Vector(0, 0, 1).scale(-1),
                "Scaling unit vector by -1 failed");

        // ================ Invalid Case =================
        // TC21: Test scaling by zero throws exception
        assertThrows(IllegalArgumentException.class, () -> V1.scale(0),
                "Scaling by zero should throw exception");
    }

    /**
     * Test method for {@link Vector#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 1, 1);
        // TC01: test to see that the subtract function works correctly
        assertEquals(new Vector(0, 1, 2), v1.subtract(v2), "subtract() wrong result");

        // =============== Boundary Values Tests ==================

        // TC10: test to see that the subtract vector from itself is zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), " cannot subtract() vector by itself ");
    }
}

