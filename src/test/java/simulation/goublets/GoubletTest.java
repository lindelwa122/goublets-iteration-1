package simulation.goublets;

import org.junit.Test;
import simulation.position.Position;
import simulation.glorzo.Glorzo;

import static org.junit.Assert.*;

public class GoubletTest {
    @Test
    public void testPosition() {
        // Test that a Goublet is aware of its position in the world
        Goublet goublet = new Goublet();
        Glorzo world = new Glorzo();

        world.addGoublet(goublet);
        assertEquals(world.getGoubletPosition(goublet), goublet.getPosition());

        Goublet goublet2 = new Goublet();
        world.addGoublet(goublet2);
        assertEquals(world.getGoubletPosition(goublet2), goublet2.getPosition());
    }
    
    @Test(expected = IllegalCallerException.class)
    public void testPositionOutsideOfWorld() {
        // Trying to get position of the goublet when it is not inside any world should throw an error
        Goublet goublet = new Goublet();
        goublet.getPosition();
    }

    @Test
    public void testSetWorld() {
        Glorzo world = new Glorzo();

        Goublet goublet = new Goublet();
        assertFalse(world.isGoubletInTheWorld(goublet));

        // Add goublet in the world
        world.addGoublet(goublet);
        assertTrue(world.isGoubletInTheWorld(goublet));
    }

    @Test(expected = IllegalCallerException.class)
    public void testGoubletNotExistInMultipleWorlds() {
        Glorzo world1 = new Glorzo();
        Glorzo world2 = new Glorzo();

        Goublet goublet = new Goublet();
        world1.addGoublet(goublet);
        world2.addGoublet(goublet);
    }

    @Test
    public void testMovement() {
        Goublet goublet = new Goublet();
        Glorzo world = new Glorzo();
        world.addGoublet(goublet);

        Position currentPosition = goublet.getPosition();
        goublet.move();
        Position newPosition = goublet.getPosition();
        assertNotEquals(currentPosition, newPosition);
    }

    @Test(expected = IllegalCallerException.class)
    public void testMovementOutsideOfWorld() {
        Goublet goublet = new Goublet();
        goublet.move();
    }
}


