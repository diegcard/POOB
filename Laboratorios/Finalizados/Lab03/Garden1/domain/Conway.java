package domain;
import java.util.*;
import java.awt.*;

/**
 * Conway is a magenta flower whose life depends on the cells that surround it
 * 
 * @author Diego Cardenas, Sebastian Cardona
 * @version 1.0.0
 */
public class Conway extends Flower{
    private static final Color CONWAY_COLOR = Color.magenta;

    /**
     * Create a new flower Conway (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     * @param name   The name of plant
     */
    public Conway(Garden garden, int row, int column, String name) {
        super(garden, row, column, name);
        this.color = CONWAY_COLOR;
    }

    
    /**
     * obtains a list with all neighbors that are cells
     * 
     * @return neighbors a list with the cells neighbors of the flower
     */
    private ArrayList<Thing> getNeighborsCell() {
        ArrayList<Thing> neighbors = new ArrayList<Thing>();
        int gardenLength = garden.getLength();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column) {
                    continue;
                }else if (i >= 0 && i < gardenLength && j >= 0 && j < gardenLength && garden.getThing(i, j) instanceof Cell){
                    Thing cell = garden.getThing(i, j);
                    Cell cell1 = (Cell) cell;
                    if(cell1.isAlive()){
                        neighbors.add(cell);
                    }
                }
            }
        }
        return neighbors;
    }
    
    /**
     * Execute the action
     */
    @Override
    public void act() {
        turn();
        ArrayList<Thing> neighborsCell= getNeighborsCell();
        if(neighborsCell.size() == 3 && state == Agent.DEAD){
            state = Agent.ALIVE;
            color = Color.magenta;
        }else if((neighborsCell.size() < 2 ||neighborsCell.size() > 3)){
            state = Agent.DEAD;
            color = Color.BLACK;
        }else if((neighborsCell.size() == 2 || neighborsCell.size() == 3)){
            
        }
    }

}
