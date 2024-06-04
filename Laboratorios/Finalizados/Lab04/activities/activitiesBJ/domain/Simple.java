package domain;

/**
 * this class represents a simple activity
 * it is composed of a name, a cost and a time
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0.0
 */
public class Simple extends Activity {
    private final Integer time;

    /**
     * Constructor of the Simple Activity
     *
     * @param name name of the activity
     * @param cost cost of the activity
     * @param time time of the activity
     */
    public Simple(String name, Integer cost, Integer time) {
        super(name, cost);
        this.time = time;
    }

    /**
     * Search for an activity
     *
     * @param name of the activity
     * @return the activity
     */
    @Override
    public Activity search(String name) {return this.name().equals(name) ? this : null;}

    /**
     * Calculate the tima that it will take to make an activity
     * Return time
     *
     * @return time
     * @throws ProjectException, if the time is not available or has an error
     */
    @Override
    public int time() throws ProjectException {

        if (time == null) {
            ProjectException e1 = new ProjectException(ProjectException.TIME_EMPTY);
            Log.record(e1);
            throw e1;
        }
        if (time < 1 || time > 24) {
            ProjectException e1 = new ProjectException(ProjectException.TIME_ERROR);
            Log.record(e1);
            throw e1;
        }
        return time;
    }

    /**
     * Return cost tha it will take to make the activity
     *
     * @return cost
     * @throws ProjectException, if the cost is not available or has an error
     */
    @Override
    public int cost() throws ProjectException {
        if (cost == null) {
            ProjectException e1 = new ProjectException(ProjectException.COST_EMPTY);
            Log.record(e1);
            throw e1;
        }
        if (cost <= 0) {
            ProjectException e1 = new ProjectException(ProjectException.COST_ERROR);
            Log.record(e1);
            throw e1;
        }
        return cost;
    }

    /**
     * Return the representation as string
     *
     * @return string representation
     * @throws ProjectException, if the data is not complete
     */
    @Override
    public String data() throws ProjectException {
        if (cost == null || time == null || name == null) {
            ProjectException e1 = new ProjectException(ProjectException.DATA_INCOMPLETE);
            Log.record(e1);
            throw e1;
        }
        return name + ". Costo:" + cost + ".Tiempo:" + time;
    }
}
