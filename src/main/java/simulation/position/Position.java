package simulation.position;

import java.text.MessageFormat;
import java.util.Objects;

public class Position implements PositionInterface {
    private final int X;
    private final int Y;

    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public Position add(Position a) {
        return new Position(a.getX()+this.X, a.getY()+this.Y);
    }

    public boolean isIn(Position topLeft, Position bottomRight) {
        boolean withinTop = this.Y <= topLeft.getY();
        boolean withinBottom = this.Y >= bottomRight.getY();
        boolean withinLeft = this.X >= topLeft.getX();
        boolean withinRight = this.X <= bottomRight.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.X == position.X && this.Y == position.Y;
    }

    public boolean greaterThan(Position position) {
        return this.X > position.getX() &&
                this.Y > position.getY();
    }

    public boolean greaterThanOrEqual(Position position) {
        return this.greaterThan(position) || this.equals(position);
    }

    public boolean lessThan(Position position) {
        return this.X < position.getX() &&
                this.Y < position.getY();
    }

    public boolean lessThanOrEqual(Position position) {
        return this.lessThan(position) || this.equals(position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.X, this.Y);
    }

    @Override
    public String toString() {
        return MessageFormat.format("({0}, {1})", this.X, this.Y);
    }
}
