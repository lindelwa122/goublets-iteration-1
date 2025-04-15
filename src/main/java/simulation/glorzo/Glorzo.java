package simulation.glorzo;

import org.jetbrains.annotations.NotNull;
import simulation.goublets.Goublet;
import simulation.position.Position;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class Glorzo {
    private final Map<Goublet, Position> goublets = new HashMap<Goublet, Position>();

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final Position TOP_LEFT_CORNER = new Position(0, 0);

    public boolean isPositionOccupied(Position position) {
        for (Map.Entry<Goublet, Position> entry : this.goublets.entrySet()) {
            Position goubletPosition = entry.getValue();

            // Check if the position lies within the area of the Goublet
            for (int dx = 0; dx < Goublet.SIZE; dx++) {
                for (int dy = 0; dy < Goublet.SIZE; dy++) {
                    Position occupiedPosition = new Position(goubletPosition.getX()+dx, goubletPosition.getY()+dy);

                    // If the position matches any part of the Goublet's area, return true
                    if (occupiedPosition.equals(position)) return true;
                }
            }
        }
        return false;
    }

    private Position getRandomEmptyPosition() {
        while (true) {
            int x = (int) Math.floor(Math.random() * WIDTH);
            int y = (int) Math.floor(Math.random() * HEIGHT);

            Position position = new Position(x, y);
            if (!this.isPositionOccupied(position)) {
                return position;
            }
        }
    }

    public boolean isGoubletInTheWorld(Goublet goublet) {
        return this.goublets.containsKey(goublet);
    }

    public void addGoublet(@NotNull Goublet goublet) {
        if (this.isGoubletInTheWorld(goublet)) {
            throw new KeyAlreadyExistsException("Goublet already exists");
        }

        this.goublets.put(goublet, this.getRandomEmptyPosition());
        goublet.setWorld(this);
    }

    public Position getGoubletPosition(@NotNull Goublet goublet) {
        return this.goublets.get(goublet);
    }

    public boolean isPositionInTheWorld(Position position) {
        return position.greaterThanOrEqual(this.TOP_LEFT_CORNER) &&
                position.lessThan(new Position(WIDTH-1, HEIGHT-1));
    }

    private boolean isMoveLegal(Position currentPosition, Position newPosition) {
        // Get Goublet size
        int goubletSize = Goublet.SIZE;

        // Calculate all possible goublet's next move
        Position upperPosition = new Position(currentPosition.getX(), currentPosition.getY()-goubletSize);
        Position lowerPosition = new Position(currentPosition.getX(), currentPosition.getY()+goubletSize);
        Position leftPosition = new Position(currentPosition.getX()-goubletSize, currentPosition.getY());
        Position rightPosition = new Position(currentPosition.getX()+goubletSize, currentPosition.getY());
        Position upperLeftPosition = new Position(currentPosition.getX()-goubletSize, currentPosition.getY()-goubletSize);
        Position upperRightPosition = new Position(currentPosition.getX()+goubletSize, currentPosition.getY()-goubletSize);
        Position lowerLeftPosition = new Position(currentPosition.getX()-goubletSize, currentPosition.getY()+goubletSize);
        Position lowerRightPosition = new Position(currentPosition.getX()+goubletSize, currentPosition.getY()+goubletSize);

        Position[] allowedPositions = new Position[]{
                upperPosition,
                lowerPosition,
                leftPosition,
                rightPosition,
                upperLeftPosition,
                upperRightPosition,
                lowerLeftPosition,
                lowerRightPosition
        };

        return Arrays.asList(allowedPositions).contains(newPosition);
    }

    public MoveResponse moveGoublet(@NotNull Goublet goublet, @NotNull Position position) {
        if (!this.isPositionInTheWorld(position)) return MoveResponse.OUT_OF_RANGE;
        if (this.isPositionOccupied(position)) return MoveResponse.OCCUPIED;
        if (!this.isMoveLegal(goublet.getPosition(), position)) return MoveResponse.ILLEGAL_MOVE;

        this.goublets.replace(goublet, position);
        return MoveResponse.SUCCESS;
    }
}
