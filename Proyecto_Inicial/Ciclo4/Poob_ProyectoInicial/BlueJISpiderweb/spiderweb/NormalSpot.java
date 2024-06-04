package spiderweb;


/**
 * This is an extension of a bridge, and it is the normal bridge,
 * that is, it behaves like a bridge
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class NormalSpot extends Spot{

    /**
     * Constructor for objects of class NormalSpot
     */
    public NormalSpot(int xPos, int yPos, int strand, String color) {
        super(xPos, yPos, strand, color);
    }
}
