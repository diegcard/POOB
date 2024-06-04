package spiderweb;


/**
 * This bridge, by eliminating it if possible, becomes a spot
 * in the same FirstStrand
 * 
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0
 */
public class TransformerBridge extends Bridge{

    /**
     * Constructor for objects of class TransformerBridge
     */
    public TransformerBridge(float x1, float y1,float x2, float y2, int FistStrand, int Distance, String direction, String color){
        super(x1,y1,x2,y2,FistStrand,Distance,direction,color);
        changeIdentificatorColor("#2558751");
    }
}
