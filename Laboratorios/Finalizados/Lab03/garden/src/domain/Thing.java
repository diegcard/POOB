package domain;
import java.awt.Color;

/**
 * Represents an interface for entities in the garden grid.
 */
public interface Thing {
    public static final int ROUND = 1;
    public static final int SQUARE = 2;
    public static final int FLOWER = 3;
    public static final int ANT = 4;

    /**
     * Defines the action to be performed by the thing during a time step.
     */
    void act();

    /**
     * Gets the column position of the entity in the colony grid.
     *
     * @return The column position of the entity.
     */
    int getColumn();

    /**
     * Gets the row position of the thing in the garden grid.
     *
     * @return The row position of the thing.
     */
    int getRow();

    /**
     * Every thing will be a shape in SQUARE for default
     * 
     * @return SQUARE
     */
    default public int shape() {
        return SQUARE;
    }

    /**
     * Gets the color of the thing.
     *
     * @return The color of the thing, which is by default set to Color.blue.
     */
    default public Color getColor() {
        return Color.blue;
    }

    /**
     * Checks if the thing is present.
     *
     * @return true if the thing is present, false otherwise.
     */
    default public boolean is() {
        return true;
    }

}
