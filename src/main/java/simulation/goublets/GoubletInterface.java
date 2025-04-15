package simulation.goublets;

import simulation.position.Position;
import simulation.glorzo.Glorzo;

import java.awt.*;

public interface GoubletInterface {
    int SIZE = 10;

    /**
     * Sets the world where the Goublet exists so it can be aware of its surroundings.
     * @param world - The world where the Goublet exists.
     */
    void setWorld(Glorzo world);

    /**
     * Gets the current position of the Goublet in the world.
     * @return The position of the Goublet.
     */
    Position getPosition();

    /**
     * Moves the Goublet to one of the 8 possible adjacent positions.
     */
    void move();

    /**
     * Draws the Goublet in the world.
     * @param g - The Graphics context used to draw the Goublet.
     */
    default void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.getPosition().getX(), this.getPosition().getY(), Goublet.SIZE, Goublet.SIZE);
    }
}
