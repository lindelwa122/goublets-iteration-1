package simulation.goublets;

import simulation.position.Position;
import simulation.glorzo.Glorzo;

public class Goublet implements GoubletInterface {
    private Glorzo world;

    public void setWorld(Glorzo world) {
        if (this.world != null) {
            throw new IllegalCallerException("A goublet cannot exist in more than one world");
        }

        this.world = world;
    }

    private void validateWorld() {
        if (this.world == null) {
            throw new IllegalCallerException("You cannot get position unless the goublet exists in a world");
        }
    }

    public Position getPosition() {
        this.validateWorld();
        return this.world.getGoubletPosition(this);
    }

    private Position[] getPossibleNextPositions(Position currentPosition) {
        Position upperPosition = new Position(currentPosition.getX(), currentPosition.getY()-SIZE);
        Position lowerPosition = new Position(currentPosition.getX(), currentPosition.getY()+SIZE);
        Position leftPosition = new Position(currentPosition.getX()-SIZE, currentPosition.getY());
        Position rightPosition = new Position(currentPosition.getX()+SIZE, currentPosition.getY());
        Position upperLeftPosition = new Position(currentPosition.getX()-SIZE, currentPosition.getY()-SIZE);
        Position upperRightPosition = new Position(currentPosition.getX()+SIZE, currentPosition.getY()-SIZE);
        Position lowerLeftPosition = new Position(currentPosition.getX()-SIZE, currentPosition.getY()+SIZE);
        Position lowerRightPosition = new Position(currentPosition.getX()+SIZE, currentPosition.getY()+SIZE);

        return new Position[]{
            upperPosition,
            lowerPosition,
            leftPosition,
            rightPosition,
            upperLeftPosition,
            upperRightPosition,
            lowerLeftPosition,
            lowerRightPosition
        };
    }

    public void move() {
        this.validateWorld();

        while (true) {
            Position[] positions = this.getPossibleNextPositions(this.getPosition());
            int randPos = (int) Math.floor(Math.random() * positions.length);
            Position position = positions[randPos];
            if (this.world.isPositionInTheWorld(position) && !this.world.isPositionOccupied(position)) {
                this.world.moveGoublet(this, position);
                break;
            }
        }
    }
}
