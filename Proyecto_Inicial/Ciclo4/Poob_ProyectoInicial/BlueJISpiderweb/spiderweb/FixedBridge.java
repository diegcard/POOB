package spiderweb;


/**
 * This is an extension of a bridge, and it is the fixed bridge,
 * this bridge once placed cannot be removed
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class FixedBridge extends Bridge{

    /**
     * Constructor for objects of class FixedBridge
     */
    public FixedBridge(float x1, float y1,float x2, float y2, int FistStrand, int Distance, String direction, String color){
        super(x1,y1,x2,y2,FistStrand,Distance,direction,color);
        changeIdentificatorColor("cyan");
    }
}
