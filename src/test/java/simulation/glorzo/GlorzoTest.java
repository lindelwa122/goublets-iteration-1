package simulation.glorzo;

import org.junit.Test;
import simulation.goublets.Goublet;
import simulation.position.Position;

import static org.junit.Assert.*;

public class GlorzoTest {
    @Test
    public void testIsPositionOccupied() {
        Position position = new Position(50, 50);

        Glorzo world = new Glorzo();
        assertFalse(world.isPositionOccupied(position));

        Goublet goublet = new Goublet();
        world.addGoublet(goublet);

        Position goubletPos = goublet.getPosition();
        assertTrue(world.isPositionOccupied(goubletPos));
    }

    @Test
    public void testGoubletInTheWorld() {
        Glorzo world = new Glorzo();
        Goublet goublet = new Goublet();

        assertFalse(world.isGoubletInTheWorld(goublet));

        world.addGoublet(goublet);
        assertTrue(world.isGoubletInTheWorld(goublet));
    }

    @Test
    public void testMovingGoublet() {
        Glorzo world = new Glorzo();
        Goublet goublet = new Goublet();

        world.addGoublet(goublet);

        MoveResponse response = world.moveGoublet(goublet, new Position(-10, -10));
        assertEquals(MoveResponse.OUT_OF_RANGE, response);

        Goublet goublet1 = new Goublet();
        world.addGoublet(goublet1);

        Position goublet1Pos = goublet1.getPosition();
        MoveResponse response1 = world.moveGoublet(goublet, goublet1Pos);
        assertEquals(MoveResponse.OCCUPIED, response1);

        Position goubletPos = goublet.getPosition();
        MoveResponse response2 = world.moveGoublet(goublet, new Position(goubletPos.getX()+60, goubletPos.getY()));
        assertEquals(MoveResponse.ILLEGAL_MOVE, response2);

        Position newPosition = new Position(goubletPos.getX()+Goublet.SIZE, goubletPos.getY());
        MoveResponse response3 = world.moveGoublet(goublet, newPosition);
        assertEquals(MoveResponse.SUCCESS, response3);
        assertEquals(newPosition, goublet.getPosition());
    }
}
