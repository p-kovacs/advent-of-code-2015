package pkovacs.util.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {

    @Test
    public void test() {
        var a = Vector.ORIGIN;
        var b = new Vector(42, 12);

        assertEquals(b, a.add(b));

        a = a.add(b).sub(new Vector(2, 2));
        assertEquals(new Vector(40, 10), a);
        assertEquals(new Vector(-40, -10), a.neg());

        assertEquals(new Vector(10, -40), a.rotateRight());
        assertEquals(new Vector(-40, -10), a.rotateRight().rotateRight());
        assertEquals(new Vector(-10, 40), a.rotateRight().rotateRight().rotateRight());
        assertEquals(new Vector(40, 10), a.rotateRight().rotateRight().rotateRight().rotateRight());

        assertEquals(new Vector(-10, 40), a.rotateLeft());
        assertEquals(new Vector(-40, -10), a.rotateLeft().rotateLeft());
        assertEquals(new Vector(10, -40), a.rotateLeft().rotateLeft().rotateLeft());
        assertEquals(new Vector(40, 10), a.rotateLeft().rotateLeft().rotateLeft().rotateLeft());

        var c = new Vector(42, 12);
        var d = new Vector(42, 12);
        assertEquals(0, Vector.dist(c, d));
        d = d.rotateRight();
        assertEquals(42 + 12 + 30, Vector.dist(c, d));
        c = c.rotateLeft();
        assertEquals(c.length() + d.length(), c.dist(d));
        c = c.neg();
        assertEquals(0, c.dist(d));

        var e = new Vector(42, 12);
        assertEquals(Vector.ORIGIN, e.multiply(0));
        assertEquals(e, e.multiply(1));
        assertEquals(e.add(e), e.multiply(2));
        assertEquals(e.add(e).add(e).add(e).add(e), e.multiply(5));

        assertEquals(Vector.ORIGIN, Vector.ORIGIN.neg());
        assertEquals(Vector.ORIGIN, Vector.ORIGIN.rotateRight());
        assertEquals(Vector.NORTH, Vector.ORIGIN.add(Vector.NORTH));
        assertEquals(Vector.SOUTH, Vector.SOUTH.add(Vector.ORIGIN));
        assertEquals(Vector.EAST, Vector.NORTH.rotateRight());
        assertEquals(Vector.WEST, Vector.SOUTH.neg().rotateLeft());
    }

}
