package domain;

import java.awt.Color;

/**
 * Information about a Flower<br>
 * <b>(garden,row,column,time,state,nextState, color)</b><br>
 * <br>
 */
public class Flower extends Agent implements Thing {
    protected Color color;
    protected Garden garden;
    protected int row, column;
    protected String name;
    protected int ticTacCount1 = 0;

    /**
     * Create a new flower (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     */
    public Flower(Garden garden, int row, int column, String name) {
        this.garden = garden;
        this.row = row;
        this.column = column;
        color = Color.red;
        this.name = name;
        garden.setThing(row, column,(Thing) this);
        state = Agent.ALIVE;
    }
    
    /**
     * Returns the shape
     *
     * @return FLOWER
     */
    @Override
    public final int shape() {
        return Thing.FLOWER;
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
     * Returns the column
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
     * The flower Execute an action
     */
    @Override
    public void act() {
        ticTacCount1++;
        switch (ticTacCount1) {
            case 1:
                color = Color.orange;
                break;
            case 3:
                color = Color.BLACK;
                state = Agent.DEAD;
                break;
            case 5:
                state = Agent.ALIVE;
                color = Color.red;
                ticTacCount1 = 0;
                break;
            default:
                break;
        }
    }

    /**
     * gets the garden where the flower are
     * @return garden
     */
    public Garden getGarden() {
        return garden;
    }

    /**
     * move the flower to an specific place
     * @param newRow
     * @param newColumn
     */
    public void moveTo(int newRow, int newColumn) {
        garden.setThing(row, column, null);
        if (garden.getThing(newRow,newColumn) instanceof Water){
            this.state = Agent.DEAD;
        }else{
            garden.setThing(newRow, newColumn, this);
            setPosition(newRow,newColumn); 
        }
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
    protected static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Check  if a position (row and column) within the garden grid is valid.
     *
     * @param newRow    The row to check
     * @param newColumn The Column to check
     * @return true if the position is valid, otherwise false
     */
    protected boolean isValidPosition(int newRow, int newColumn) {
        return newRow >= 0 && newRow < garden.getLength() && newColumn >= 0 && newColumn < garden.getLength();
    }

    //a random position to move the flower
    private int randomMove(){
        return (int) (Math.random() * 3) - 1;
    }
    
    /**
     * move the flower to the random adyacent box
     */
    @Override
    public void move() {
        int newRow = row + randomMove();
        int newColumn = column + randomMove();

        newRow = Math.max(0, Math.min(newRow, garden.getLength() - 1));
        newColumn = Math.max(0, Math.min(newColumn, garden.getLength() - 1));
        if (!(garden.getThing(newRow,newColumn) instanceof Water || garden.getThing(newRow,newColumn) == null)){
            newRow = row; 
            newColumn = column;
        } 
        garden.setThing(row, column, null);
        if (garden.getThing(newRow,newColumn) instanceof Water){
            state = Agent.DEAD;
        }
        else{
            garden.setThing(newRow, newColumn, this);
            setPosition(newRow,newColumn);
        }
    }
    
    /**
     * Sets the position of the Flower to the specified row and column.
     *
     * @param newRow The new row for the Ant.
     * @param newColumn The new column for the Ant.
     */
    public void setPosition(int newRow, int newColumn){
        row = newRow;
        column = newColumn;
    }
}
