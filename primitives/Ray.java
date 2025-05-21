package primitives;

/**
 * a Class Ray; Represents a ray in three-dimensional space, defined by a direction vector and an origin point.
 *
 * @author Raphael
 */
public class Ray {
    /**
     * Direction vector of the ray
     */
    private final Vector direction;

    /**
     * Origin point of the ray
     */
    private final Point origin; // Origin point of the ray

    /**
     * Constructs a Ray with the specified direction vector and origin point.
     *
     * @param direction The direction vector of the ray.
     * @param origin    The origin point of the ray.
     */
    public Ray(Vector direction, Point origin) {
        this.direction = direction.normalize(); // Normalize the direction vector
        this.origin = origin;
    }

    /**
     * Returns a string representation of the ray.
     *
     * @return The string representation of the ray.
     */
    @Override
    public String toString() {
        return "Ray [direction=" + direction + ", origin=" + origin + "]";
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return The direction vector of the ray.
     */
    public Vector direction() {
        return direction;
    }

    /**
     * Returns the origin point of the ray.
     *
     * @return The origin point
     * of the ray.
     */
    public Point origin() {
        return origin;
    }
}