package pkovacs.util.data;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TileTest {

    @Test
    public void test() {
        var a = new Tile(12, 42);
        var b = new Tile(42, 12);
        var c = new Tile(42, 12);

        assertEquals(12, a.row());
        assertEquals(42, a.col());
        assertNotEquals(a, b);
        assertEquals(b, c);

        assertTrue(a.isValid(13, 43));
        assertFalse(a.isValid(12, 43));
        assertFalse(a.isValid(13, 42));

        assertEquals(List.of(
                        new Tile(11, 42),
                        new Tile(12, 43),
                        new Tile(13, 42),
                        new Tile(12, 41)),
                a.neighbors());
        assertEquals(List.of(new Tile(11, 42), new Tile(12, 41)),
                a.neighbors(n -> n.row() <= a.row() && n.col() <= a.col()));

        assertEquals(new Tile(11, 42), a.neighbor('n'));
        assertEquals(new Tile(13, 42), a.neighbor('s'));
        assertEquals(new Tile(12, 43), a.neighbor('E'));
        assertEquals(new Tile(12, 41), a.neighbor('W'));

        assertEquals(List.of(
                        new Tile(11, 42),
                        new Tile(11, 43),
                        new Tile(12, 43),
                        new Tile(13, 43),
                        new Tile(13, 42),
                        new Tile(13, 41),
                        new Tile(12, 41),
                        new Tile(11, 41)),
                a.eightNeighbors());

        assertTrue(a.neighbors().stream().allMatch(n -> Tile.dist(a, n) == 1));
        assertEquals(4, a.validNeighbors(14, 44).size());
        assertEquals(2, a.validNeighbors(13, 43).size());
        assertEquals(1, a.validNeighbors(12, 43).size());
        assertEquals(1, a.validNeighbors(13, 42).size());
        assertEquals(0, a.validNeighbors(12, 42).size());

        assertTrue(a.eightNeighbors().stream().allMatch(n -> Tile.dist(a, n) <= 2));
        assertEquals(12, a.eightNeighbors().stream().mapToInt(a::dist).sum());
    }

}
