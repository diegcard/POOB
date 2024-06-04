package domain;
import java.awt.*;
import java.util.*;

/**
 * a cell is an agent that nourishes conway flowers
 * 
 * @author Sebastian Cardona- Diego Cardenas 
 * @version 1.0.0
 */
public class Cell extends Agent implements Thing{
    protected Color color;
    protected Garden garden;
    protected int row,column;
    protected int ticTacCount;
    protected boolean[][] visited;
    
    /**
     * Constructor for objects of class Cell
     */
    public Cell(Garden garden, int row, int column){
        this.garden = garden;
        this.row = row;
        this.column = column; 
        state = Agent.ALIVE;
        garden.setThing(row, column, (Thing) this);
        color = Color.cyan;
        ticTacCount = 0;  
        this.visited = new boolean[garden.getLength()][garden.getLength()];
    }
    
    /**
     * of a null box, obtains a list with neighbors of type cells ALIVE
     * that have the box
     * 
     * @param row    the row of the null box
     * @param column the column of the null box
     * @return neighbors a list with the cell neighbors of the null bok 
     */
    public ArrayList<Thing> getNeighborsCellOfNull(int row, int column) {
        ArrayList<Thing> neighbors = new ArrayList<Thing>();
        if(garden.getThing(row, column) == null){
            int gardenLength = garden.getLength();
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i == row && j == column) {
                        continue;
                    }else if (i >= 0 && i < gardenLength && j >= 0 && j < gardenLength && garden.getThing(i, j) instanceof Cell) {
                        Thing cell = garden.getThing(i, j);
                        Cell cell1 = (Cell) cell;
                        if(cell1.isAlive()){
                            neighbors.add(cell);
                        }
                    }
                }
            }
        }
        return neighbors;
    }
    
    //defines whether all neighboring cells have been visited
    private boolean allNeighborsInNotVisited(int row, int column){
        int gardenLength = garden.getLength();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column) {
                    continue;
                }else if (i >= 0 && i < gardenLength && j >= 0 && j < gardenLength && garden.getThing(i, j) instanceof Cell) {
                    if(visited[i][j] == true){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    //init the bool matrix of visited in false
    private void initVisitedInFalse(){
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                visited[r][c] = false;
            }
        }
    }
    
    /**
     * Execute an action
     */
    @Override
    public void act(){
        initVisitedInFalse();
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                if(garden.getThing(r,c) == null){
                    ArrayList<Thing> neighborsCel = getNeighborsCellOfNull(r,c);
                    if(neighborsCel.size() == 3 && allNeighborsInNotVisited(r,c)){
                        Cell cell = new Cell(garden,r,c);
                        visited[r][c] = true;
                    }
                }
            }
        }
    }

    
    /**
     * Returns the shape
     *
     * @return Cell
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
    
    public void move(){}
}
