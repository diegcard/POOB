package domain;


/**
 * this class represents an activity that can be simple or composed
 * @author Cardenas-Cardona
 * @version 1.0
 */
public abstract class Activity{
    
    protected String name;
    protected Integer cost;
    protected Integer time;


    /**
     * Constructs a new activity
     * @param name
     * @param cost
     */
    public Activity(String name, Integer cost){
        this.name=name;
        this.cost=cost;
    }
    
    /**
     * Return the name
     * @return name
     */
    public String name(){
        return name;
    }

 
    /**
     * Calculate the tima that it will take to make an activity 
     * Return time
     * @return time
     * @throws ProjectException, if the time is not available or has an error
     */
    public abstract int time() throws ProjectException;
    
    
    /**
     * Return cost tha it will take to make the activity
     * @return cost
     * @throws ProjectException, if the cost is not available or has an error
     */    
    public abstract int cost() throws ProjectException;

    /**
     * Search for an activity
     * @param name of the activity
     * @return the activity
     */
    public abstract Activity search(String name);
    
    /**
     * gets the time of the activity
     * 
     * @return time
     */
    public int getTime(){
        if(time != null) return time;
        return 0;
    }
    
    /**
     * Return the representation as string
     * @return
     * @throws ProjectException, if the data is not complete
     */    
    public abstract String data() throws ProjectException;
}
