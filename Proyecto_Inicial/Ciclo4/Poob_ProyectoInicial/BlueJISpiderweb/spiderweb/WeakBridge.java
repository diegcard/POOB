package spiderweb;

/**
 * This bridge is removed if the spider passes over it
 * 
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class WeakBridge extends Bridge{

    /**
     * Constructor for objects of class WeakBridge
     */
    public WeakBridge(float x1, float y1,float x2, float y2, int FistStrand, int Distance, String direction, String color){
        super(x1,y1,x2,y2,FistStrand,Distance,direction,color);
        changeIdentificatorColor("lightgray");
    }

}
