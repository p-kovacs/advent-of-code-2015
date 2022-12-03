package com.github.pkovacs.util.data;

/**
 * Represents a position vector in 2D space as an immutable pair of {@code long} values: x and y coordinates.
 * Provides methods for vector operations (addition, rotation, etc.) and for obtaining the Manhattan distance
 * between two vectors.
 * <p>
 * The coordinates are interpreted as usual in Math: {@code (0,1)} means {@link #NORTH}, {@code (0,-1)} means
 * {@link #SOUTH}, {@code (1,0)} means {@link #EAST}, and {@code (0,1)} means {@link #WEST}. And the {@link #ORIGIN}
 * is {@code (0,0)}.
 *
 * @see Point
 */
public record Vector(long x, long y) {

    /** The origin vector, {@code (0,0)}. */
    public static final Vector ORIGIN = new Vector(0, 0);

    /** The unit vector with "north" direction, {@code (0,1)}. */
    public static final Vector NORTH = new Vector(0, 1);

    /** The unit vector with "south" direction, {@code (0,-1)}. */
    public static final Vector SOUTH = new Vector(0, -1);

    /** The unit vector with "east" direction, {@code (1,0)}. */
    public static final Vector EAST = new Vector(1, 0);

    /** The unit vector with "west" direction, {@code (-1,0)}. */
    public static final Vector WEST = new Vector(-1, 0);

    /**
     * Returns a new vector obtained by adding the given vector to this one.
     */
    public Vector add(Vector v) {
        return new Vector(x + v.x, y + v.y);
    }

    /**
     * Returns a new vector obtained by adding the given two vectors.
     */
    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    /**
     * Returns a new vector obtained by subtracting the given vector from this one.
     */
    public Vector sub(Vector v) {
        return new Vector(x - v.x, y - v.y);
    }

    /**
     * Returns a new vector obtained by subtracting the given second vector from the first one.
     */
    public static Vector sub(Vector a, Vector b) {
        return new Vector(a.x - b.x, a.y - b.y);
    }

    /**
     * Returns the negative (opposite) of this vector.
     */
    public Vector neg() {
        return new Vector(-x, -y);
    }

    /**
     * Returns a new vector obtained by multiplying this vector by the given scalar factor.
     */
    public Vector multiply(long factor) {
        return new Vector(x * factor, y * factor);
    }

    /**
     * Returns a vector obtained by rotating this vector by 90 degrees to the right.
     */
    public Vector rotateRight() {
        return new Vector(y, -x);
    }

    /**
     * Returns a vector obtained by rotating this vector by 90 degrees to the left.
     */
    public Vector rotateLeft() {
        return new Vector(-y, x);
    }

    /**
     * Returns the length of this vector (the Manhattan distance from {@link #ORIGIN}).
     */
    public long length() {
        return Math.abs(x) + Math.abs(y);
    }

    /**
     * Returns the Manhattan distance between this vector and the given vector.
     */
    public long dist(Vector v) {
        return sub(this, v).length();
    }

    /**
     * Returns the Manhattan distance between the given two vectors.
     */
    public static long dist(Vector a, Vector b) {
        return sub(a, b).length();
    }

}
