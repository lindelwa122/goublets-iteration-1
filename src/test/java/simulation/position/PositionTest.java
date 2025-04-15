package simulation.position;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void testXandY() {
        Position position = new Position(1, 2);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());

        Position position1 = new Position(100, 150);
        assertEquals(100, position1.getX());
        assertEquals(150, position1.getY());
    }

    @Test
    public void testEquals() {
        Position position = new Position(12, 18);
        Position position1 = new Position(12, 18);
        assertEquals(position1, position);

        Position position2 = new Position(17, 19);
        Position position3 = new Position(19, 19);
        assertNotEquals(position3, position2);
    }

    @Test
    public void testGreaterThan() {
        Position position = new Position(18, 20);
        assertTrue(position.greaterThan(new Position(4, 19)));
        assertFalse(position.greaterThan(new Position(18, 20)));
        assertFalse(position.greaterThan(new Position(19, 17)));
        assertFalse(position.greaterThan(new Position(20, 25)));
    }

    @Test
    public void testGreaterThanOrEqual() {
        Position position = new Position(18, 20);
        assertTrue(position.greaterThanOrEqual(new Position(4, 19)));
        assertTrue(position.greaterThanOrEqual(new Position(18, 20)));
        assertFalse(position.greaterThanOrEqual(new Position(19, 17)));
        assertFalse(position.greaterThanOrEqual(new Position(20, 25)));
    }

    @Test
    public void testLessThan() {
        Position position = new Position(18, 20);
        assertTrue(position.lessThan(new Position(30, 45)));
        assertFalse(position.lessThan(new Position(18, 20)));
        assertFalse(position.lessThan(new Position(20, 19)));
        assertFalse(position.lessThan(new Position(4, 19)));
    }

    @Test
    public void testLessThanOrEqual() {
        Position position = new Position(18, 20);
        assertTrue(position.lessThanOrEqual(new Position(30, 45)));
        assertTrue(position.lessThanOrEqual(new Position(18, 20)));
        assertFalse(position.lessThanOrEqual(new Position(20, 19)));
        assertFalse(position.lessThanOrEqual(new Position(4, 19)));
    }

    @Test
    public void testAdd() {
        Position position = new Position(1, 3);
        Position position1 = new Position(5, 1);
        Position total = new Position(position1.getX()+ position.getX(), position1.getY()+position.getY());
        assertEquals(total, position.add(position1));
    }

    @Test
    public void insideRectangularRegion() {
        Position topLeft = new Position(-20, 20);
        Position bottomRight = new Position(20,-20);
        assertTrue((new Position(10,10)).isIn(topLeft, bottomRight));
        assertFalse((new Position(10,30)).isIn(topLeft, bottomRight));
        assertFalse((new Position(10,-30)).isIn(topLeft, bottomRight));
        assertFalse((new Position(30,10)).isIn(topLeft, bottomRight));
        assertFalse((new Position(-30,10)).isIn(topLeft, bottomRight));
    }
}
