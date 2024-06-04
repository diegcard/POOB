package spiderweb;


/**
 * This is an extension of a bridge, and it is the normal bridge,
 * that is, it behaves like a bridge
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class NormalBridge extends Bridge{
    
    /**
     * Constructor for objects of class NormalBridge
     */
    public NormalBridge(float x1, float y1,float x2, float y2, int FistStrand, int Distance, String direction, String color){
        super(x1,y1,x2,y2,FistStrand,Distance,direction,color);
    }
}
