package spiderweb;


/**
 *If the spider reaches this spot, eliminate it
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class KillerSpot extends Spot{

    /**
     * Constructor for objects of class KillerSpot
     */
    public KillerSpot(int xPos, int yPos, int strand, String color) {
        super(xPos, yPos, strand, color);
        changeIdentificatorColor("red");
    }
}
