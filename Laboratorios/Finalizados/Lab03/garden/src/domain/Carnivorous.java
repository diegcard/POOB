package domain;

import java.awt.Color;

/**
 * Carnivorous is a flower that go ahead and eat the rest of the flowers
 *
 * @author Cardenas - Cardona
 * @version 1.0.0
 */
public class Carnivorous extends Flower {
    private static final Color CARNIVOROUS_COLOR = Color.BLUE;

    /**
     * Constructor for objects of class Carnivorous
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     */
    public Carnivorous(Garden garden, int row, int column, String name){
        super(garden, row, column, name);
        this.color = CARNIVOROUS_COLOR;
    }

    /**
     * Execute the action
     */
    @Override
    public void act() {
        turn();
        moveToNearestFlower();
    }

    // find the nearest Flower from the carnivorous
    private int[] findNearestFlower() {
        int minDistance = Integer.MAX_VALUE;
        int[] nearestFlower = null;
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                Thing thing = garden.getThing(r, c);
                if (thing instanceof Flower && !(thing instanceof Carnivorous)) {
                    int distance = calculateDistance(row, column, r, c);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestFlower = new int[]{r, c};
                    }
                }
            }
        }
        return nearestFlower;
    }

    /**
     * Move the carnivorous to the nearest flower
     */
    private void moveToNearestFlower() {
        int[] nearestFlower = findNearestFlower();
        if (nearestFlower != null) {
            int newRow = row;
            int newColumn = column;
            double minDistance = Integer.MAX_VALUE;
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = column - 1; c < column + 2; c++) {
                    int distance = calculateDistance(r, c, nearestFlower[0], nearestFlower[1]);
                    boolean flag = isValidPosition(r, c) && ((garden.getThing(r, c) instanceof Flower) || garden.getThing(r, c) == null);
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
