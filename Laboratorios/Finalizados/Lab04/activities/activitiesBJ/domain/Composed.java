package domain;

import java.util.ArrayList;

/**
 * this class represents a composed activity that can be parallel or sequential
 * it is composed of other activities
 *
 * @author Sebastian Cardona
 * @author Diego Cardenas
 * @version 1.0.0
 */
public class Composed extends Activity {

    private final boolean parallel;
    private final ArrayList<Activity> activities;

    /**
     * Constructs a new composed activity
     *
     * @param name     name of the activity
     * @param cost     cost of the activity
     * @param parallel type of composed activity
     */
    public Composed(String name, Integer cost, boolean parallel) {
        super(name, cost);
        this.parallel = parallel;
        activities = new ArrayList<>();
    }

    /**
     * Add a new activity
     *
     * @param a activity to add
     */
    public void add(Activity a) {
        activities.add(a);
    }

    /**
     * Return cost tha it will take to make the activity
     *
     * @return cost
     * @throws ProjectException, if the cost is not available or has an error
     */
    @Override
    public int cost() throws ProjectException {
        if (activities.isEmpty()) {
            ProjectException e1 = new ProjectException(ProjectException.COMPOSED_EMPTY);
            Log.record(e1);
            throw e1;
        }
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
     * calculates the total time it will take to execute a composed
     * activity, depending on whether the activity is performed sequentially or parallel
     *
     * @return time total
     */
    @Override
    public int time() throws ProjectException {
        if (activities.isEmpty()) {
            ProjectException e1 = new ProjectException(ProjectException.COMPOSED_EMPTY);
            Log.record(e1);
            throw e1;
        }
        int maxtime = Integer.MIN_VALUE;
        time = 0;
        for (Activity a : activities) {
            time += a.time();
            if (a.time() > maxtime) maxtime = a.time();
        }
        if (parallel) {
            time = maxtime;
        }
        return time;
    }


    /**
     * Calculates an estimated time using default values when necessary
     *
     * @param dUnknow valor por defecto para actividades desconocidas
     * @param dError  valor por defecto para actividades con error
     * @param dEmpty  valor por defecto para actividades vacias
     * @return el tiempo estimado
     **/
    public int time(int dUnknow, int dError, int dEmpty) {
        try {
            return time();
        } catch (ProjectException e) {
            switch (e.getMessage()) {
                case ProjectException.COMPOSED_EMPTY:
                    time = dUnknow;
                    return dUnknow;
                case ProjectException.TIME_ERROR:
                    time = dError;
                    return dError;
                case ProjectException.TIME_EMPTY:
                    time = dEmpty;
                    return dEmpty;
                default:
                    return 0;
            }
        }
    }

    /**
     * Calculate an estimated time considering the modality, if is possible.
     *
     * @param modality ['A'(average), 'M' (ax)] Use the average or maximum time of known activities to estimate unknown ones or those with error.
     * @return 0
     * @throws ProjectException IMPOSSIBLE, if it can't be calculated
     **/
    public int time(char modality) throws ProjectException {
        if (activities.isEmpty()) {
            ProjectException e1 = new ProjectException(ProjectException.COMPOSED_EMPTY);
            Log.record(e1);
            throw e1;
        }
        int totalTime = 0, count = 0, maxTime = Integer.MIN_VALUE;
        ArrayList<Integer> errorIndices = new ArrayList<>();
        for (Activity a : activities) {
            try {
                totalTime += a.time();
                count++;
                if (a.time() > maxTime) maxTime = a.time();
            } catch (ProjectException e) {
                errorIndices.add(activities.indexOf(a));
            }
        }
        if (count == 0 || !(modality == 'A' || modality == 'M')) {
            ProjectException e1 = new ProjectException(ProjectException.IMPOSIBLE);
            Log.record(e1);
            throw e1;
        }
        int estimatedTime = modality == 'A' ? totalTime / count : maxTime;
        for (int i : errorIndices) activities.get(i).time = estimatedTime;
        if (parallel) time = maxTime;
        else time = totalTime + estimatedTime * (errorIndices.size());
        return time;
    }

    /**
     * Calculates a time of a subactivity
     *
     * @return time of the subactivity
     * @throws ProjectException UNKNOWN, if it doesn't exist. IMPOSSIBLE, if it can't be calculated
     */
    public int time(String activity) throws ProjectException {
        Activity a = search(activity);
        if (a == null) {
            ProjectException e1 = new ProjectException(ProjectException.UNKNOWN);
            Log.record(e1);
            throw e1;
        }
        try {
            return a.time();
        } catch (ProjectException e) {
            ProjectException e1 = new ProjectException(ProjectException.IMPOSIBLE);
            Log.record(e1);
            throw e1;
        }
    }

    /**
     * Search for an activity by name
     *
     * @param name the name of the activity to search
     * @return the activity if it exists, null otherwise
     */
    @Override
    public Activity search(String name) {
        for (Activity a : activities) {
            if (a.name().equals(name)) return a;
            Activity found = a.search(name);
            if (found != null) return found;
        }
        return null;
    }

    /**
     * Return the representation as string
     *
     * @return string representation
     * @throws ProjectException, if the data is not complete
     */
    @Override
    public String data() throws ProjectException {
        if (activities.isEmpty()) {
            ProjectException e1 = new ProjectException(ProjectException.COMPOSED_EMPTY);
            Log.record(e1);
            throw e1;
        }
        if (cost == null || name == null) {
            ProjectException e1 = new ProjectException(ProjectException.DATA_INCOMPLETE);
            Log.record(e1);
            throw e1;
        }
        StringBuffer answer = new StringBuffer();
        answer.append(name + ". Tipo " + (parallel ? "Paralela" : "Secuencial") + ".");
        for (Activity b : activities) {
            answer.append("\n\t" + b.data());
        }
        return answer.toString();
    }
}
