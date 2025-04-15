package simulation.position;

/**
 * Represents a two-dimensional position with basic comparison and arithmetic operations.
 */
public interface PositionInterface {

    /**
     * Gets the x-coordinate of this position.
     *
     * @return the x value
     */
    int getX();

    /**
     * Gets the y-coordinate of this position.
     *
     * @return the y value
     */
    int getY();

    /**
     * Returns a new position resulting from the addition of this position and the specified position.
     *
     * @param other the position to add
     * @return a new Position representing the sum
     */
    Position add(Position other);

    /**
     * Determines whether this position is within a rectangular area defined by two corner positions.
     *
     * @param topLeft     the top-left corner of the area
     * @param bottomRight the bottom-right corner of the area
     * @return true if this position is within the area; false otherwise
     */
    boolean isIn(Position topLeft, Position bottomRight);

    /**
     * Checks if this position is greater than the specified position.
     *
     * @param other the position to compare against
     * @return true if this position is greater; false otherwise
     */
    boolean greaterThan(Position other);

    /**
     * Checks if this position is greater than or equal to the specified position.
     *
     * @param other the position to compare against
     * @return true if this position is greater than or equal; false otherwise
     */
    boolean greaterThanOrEqual(Position other);

    /**
     * Checks if this position is less than the specified position.
     *
     * @param other the position to compare against
     * @return true if this position is less; false otherwise
     */
    boolean lessThan(Position other);

    /**
     * Checks if this position is less than or equal to the specified position.
     *
     * @param other the position to compare against
     * @return true if this position is less than or equal; false otherwise
     */
    boolean lessThanOrEqual(Position other);
}
