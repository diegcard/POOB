package domain;

import java.util.*;
/**
 * The garden is the place where all things and agents are located, where they will also interact with each other.
 * 
 * @author Diego Cardenas, Sebastian Cardona
 * @version 1.0.0
 */

public class Garden {
    static public int LENGTH = 40;
    private final Thing[][] garden;
    private boolean actInCell;

    /**
     * Create a new garden
     */
    public Garden() {
        garden = new Thing[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                garden[r][c] = null;
            }
        }
        setThing(0, 0, new Water());
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                setThing(LENGTH - i, LENGTH - j, new Water());
            }
        }
        someThings();
    }

    /**
     * Get the length
     *
     * @return LENGTH
     */
    public int getLength() {
        return LENGTH;
    }

    /**
     * Get the thing in the garden.
     *
     * @param r Row position.
     * @param c Column Position.
     * @return The Thing at the specified position, or null if nothing is present.
     */
    public Thing getThing(int r, int c) {
        return garden[r][c];
    }

    /**
     * Set the thing in the garden.
     *
     * @param r The row in the Garden.
     * @param c The column in the Garden.
     * @param e The Thing to set at the specified position.
     */
    public void setThing(int r, int c, Thing e) {
        garden[r][c] = e;
    }

    /**
     * Create some things in the garden.
     */
    public void someThings() {
        //Normal flower
        new Flower(this, 10, 10, "rose");
        new Flower(this, 15, 15, "violet");
        //carnivorous flower
        new Carnivorous(this, 30, 30, "venus");
        new Carnivorous(this, 10, 5, "sundeuos");
        // Sand 
        new Sand(this, 0, 39, "tatacoa");
        new Sand(this, 0, 38, "sahara");
        //New Flower Cane
        new Cane(this, 20, 25, "Diego");
        new Cane(this, 8, 30, "Sebastian");
        // New Ant
        new Ant(this, 15, 21, "Diego");
        new Ant(this, 39, 1, "Sebastian");
        // new Cell
        new Cell(this,15,20);
        new Cell(this,14,21);
        new Cell(this,15,22);
        //new conway
        //new Conway(this,15,21,"Pepe");
        new Conway(this,1,23,"Mary");
        /*new Cell(this,17,21);
        new Cell(this,17,22);
        new Conway(this,18,21,"Mary");*/
    }

    /**
     * Performs a time step (tic-tac) for the entities within the garden grid.
     */
    public void ticTac() {
        actInCell = false;
        boolean[][] visited = new boolean[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                if (!visited[r][c]){
                    Thing thing = garden[r][c];
                    if (thing != null && !(thing instanceof Cell)) {
                        thing.act();
                        visited[thing.getRow()][thing.getColumn()] = true;
                    }else if(thing instanceof Cell && !actInCell){
                        actInCell = true;
                        thing.act();
                        visited[thing.getRow()][thing.getColumn()] = true;
                    }
                }
            }
        }
    }
}
