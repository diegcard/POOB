package domain;


import java.awt.*;

/**
 * ant is a new thing that is responsible for following and eating the carnivores
 * 
 * @author Diego Cardenas, Sebastian Cardona
 * @version 1.0.0
 */
public class Ant extends Agent implements Thing{
    protected char nextState;
    protected Color color;
    protected Garden garden;
    protected int row,column;
    protected int ticTacCount;
    protected String name;

    /**
     * Constructs an Ant object with the specified parameters.
     *
     * @param colony The colony to which the Ant belongs.
     * @param row The row in the colony grid.
     * @param column The column in the colony grid.
     * @param name The name of the Ant.
     */
    public Ant(Garden garden, int row, int column, String name) {
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.name = name; 
        nextState = Agent.ALIVE;
        garden.setThing(row, column, (Thing) this);
        color = Color.pink;
        ticTacCount = 0;  
    }

    /**
     * Returns the shape
     *
     * @return ANT
     */
    @Override
    public final int shape() {
        return Thing.ROUND;
    }

    /**
     * Returns the row
     *
     * @return row
     */
    @Override
    public final int getRow() {
        return row;
    }

    /**
     * Returns tha column
     *
     * @return column
     */
    @Override
    public final int getColumn() {
        return column;
    }


    /**
     * Returns the color
     *
     * @return color
     */
    @Override
    public final Color getColor() {
        return color;
    }
    
    /**
     * Execute the action
     */
    @Override
    public void act() {
        turn();
        moveToNearestCarnivorous();
    }
    
    /**
     * Calculate the position between two points.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The calculated distance.
     */
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    /**
     * find the nearest carnivorous
     */
    private int[] findNearestCarnivorous() {
        int minDistance = Integer.MAX_VALUE;
        int[] nearestStand = null;
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                Thing thing = garden.getThing(r, c);
                if (thing instanceof Carnivorous) {
                    int distance = calculateDistance(row, column, r, c);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestStand = new int[]{r, c};
                    }
                }
            }
        }
        return nearestStand;
    }
    
    /**
     * Check  if a position (row and column) within the garden grid is valid.
     *
     * @param newRow    The row to check
     * @param newColumn The Column to check
     * @return true if the position is valid, otherwise false
     */
    private boolean isValidPosition(int newRow, int newColumn) {
        return newRow >= 0 && newRow < garden.getLength() && newColumn >= 0 && newColumn < garden.getLength();
    }
    
    /**
     * move the ant to the random adyacent box
     * @param newRow
     * @param newCoulumn
     */
    public void moveTo(int newRow, int newColumn) {
        garden.setThing(row, column, null);
        if (garden.getThing(newRow,newColumn) instanceof Water){
            this.state = Agent.DEAD;
        }else{
            garden.setThing(newRow, newColumn, this);
            row = newRow;
            column = newColumn;  
        }
    }
    
    /**
     * move the ant to the nearest carnivorous
     */
    private void moveToNearestCarnivorous() {
        int[] nearestFlower = findNearestCarnivorous();
        if (nearestFlower != null) {
            int newRow = row;
            int newColumn = column;
            double minDistance = Integer.MAX_VALUE;
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = column - 1; c < column + 2; c++) {
                    int distance = calculateDistance(r, c, nearestFlower[0], nearestFlower[1]);
                    boolean flag = isValidPosition(r, c) && ((garden.getThing(r, c) instanceof Carnivorous) || garden.getThing(r, c) == null);
                    if (minDistance > distance && flag) {
                        minDistance = distance;
                        newRow = r;
                        newColumn = c;
                    }
                }
            }
            moveTo(newRow, newColumn);
        }
    }
    
    @Override
    public void move(){}
}
