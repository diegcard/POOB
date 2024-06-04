package spiderweb;


/**
 * Write a description of class ReturnerColorSpot here.
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class ColorSpot extends Spot{
    
    /**
     * Constructor for objects of class ReturnerColorSpot
     */
    public ColorSpot(int xPos, int yPos, int strand, String color) {
        super(xPos, yPos, strand, color);
        changeIdentificatorColor("purple");
    }
}
