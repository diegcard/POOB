package spiderweb;


/**
 * If possible, when the spider passes over this bridge, it moves to the next thread and to a 20% greater distance.
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class MobileBridge extends Bridge{

    /**
     * Constructor for objects of class MobileBridge
     */
    public MobileBridge(float x1, float y1,float x2, float y2, int FistStrand, int Distance, String direction, String color){
        super(x1,y1,x2,y2,FistStrand,Distance,direction,color);
        changeIdentificatorColor("pink");
    }

}
