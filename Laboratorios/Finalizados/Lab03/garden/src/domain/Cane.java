package domain;


import java.awt.*;

/**
 * Cane is a plant that moves towards the sand,When it is next to the sand, it prevents
 * it from losing more color and nourishes it so that it recovers its color., It is identified with the color green.
 * 
 * @author Diego Cardenas, Sebastian Cardona
 * @version 1.0.0
 */
public class Cane extends Flower{
    private static final Color CANE_COLOR = Color.green;

    /**
     * Create a new flower cane (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     * @param name   The name of plant
     */
    public Cane(Garden garden, int row, int column, String name) {
        super(garden, row, column, name);
        this.color = CANE_COLOR;
    }

    /**
     * Execute the action
     */
    @Override
    public void act() {
        turn();
        moveToNearestSand();
    }

    // find the nearest sand from the curretn position of the cane flower
    private int[] findNearestSand() {
        int minDistance = Integer.MAX_VALUE;
        int[] nearestStand = null;
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                Thing thing = garden.getThing(r, c);
                if (thing instanceof Sand) {
                    int distance = calculateDistance(row, column, r, c);
                    if (distance < 1) {
                        return new int[]{row, column};
                    }else if (distance < minDistance) {
                        minDistance = distance;
                        nearestStand = new int[]{r, c};
                    }
                }
            }
        }
        return nearestStand;
    }

    /**
     * Move the Cane to the nearest Sand box
     */
    private void moveToNearestSand() {
        int[] nearestFlower = findNearestSand();
        if (nearestFlower != null) {
            int newRow = row;
            int newColumn = column;
            double minDistance = Integer.MAX_VALUE;
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = column - 1; c < column + 2; c++) {
                    int distance = calculateDistance(r, c, nearestFlower[0], nearestFlower[1]);
                    boolean flag = isValidPosition(r, c) && !(garden.getThing(r, c) instanceof Sand) && garden.getThing(r, c) == null;
                    if (minDistance > distance && flag) {
                        minDistance = distance;
                        newRow = r;
                        newColumn = c;
                    }
                }
            }
            super.moveTo(newRow, newColumn);
        }
    }
}
