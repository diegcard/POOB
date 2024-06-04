package spiderweb;

/**
 * If the spider reaches this spot, it jumps to the next thread
 * 
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class BouncySpot extends Spot{

    /**
     * Constructor for objects of class BouncySpot
     */
    public BouncySpot(int xPos, int yPos, int strand, String color) {
        super(xPos, yPos, strand, color);
        changeIdentificatorColor("black");
    }
}
