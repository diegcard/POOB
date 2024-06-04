package domain;

/**
 * this class represents a simple activity
 * it is composed of a name, a cost and a time
 *
 * @author Cardenas-Cardona
 * @version 1.0.0
 */
public class Simple extends Activity{
    private Integer time;
    
    /**
     * Constructor of the Simple Activity
     * 
     * @param name
     * @param cost
     * @param time
     */
    public Simple(String name, Integer cost, Integer time){
        super(name,cost);
        this.time=time;
    }

    /**
     * Search for an activity
     * @param name of the activity
     * @return the activity
     */
    @Override
    public Activity search(String name){
        return this.name().equals(name) ? this : null;
    }

    
    /**
     * Calculate the tima that it will take to make an activity 
     * Return time
     * @return time
     * @throws ProjectException, if the time is not available or has an error
     */
    @Override
    public int time() throws ProjectException{
       if (time == null) throw new ProjectException(ProjectException.TIME_EMPTY);
       if (time < 1 || time>24) throw new ProjectException(ProjectException.TIME_ERROR);
       return time;
    }    
    
    /**
     * Return cost tha it will take to make the activity
     * @return cost
     * @throws ProjectException, if the cost is not available or has an error
     */    
    @Override
    public int cost() throws ProjectException{
       if (cost == null) throw new ProjectException(ProjectException.COST_EMPTY);
       if (cost <= 0) throw new ProjectException(ProjectException.COST_ERROR);
       return cost;
    }   
    
    /**
     * Return the representation as string
     * @return
     * @throws ProjectException, if the data is not complete
     */    
    @Override
    public String data(){
        return name+". Costo:" +cost+".Tiempo:"+time;
    }
}
