package domain;

import java.util.LinkedList;
import java.util.HashMap;

/**
 * This class represents a project that is composed of activities
 *
 * @author POOB
 * @version ECI 2022
 */

public class Project {
    private final HashMap<String, Activity> activities;

    /**
     * Create a Project
     */
    public Project() {
        activities = new HashMap<String, Activity>();
        try {
            addSome();
        } catch (ProjectException e) {
            e.printStackTrace();
            RuntimeException e1 = new RuntimeException("Error al inicializar Project", e);
            Log.record(e1);
            throw e1;
        }
    }

    private void addSome() throws ProjectException {
        String[][] activities = {{"Buscar datos", "50", "15", ""},
                {"Evaluar datos", "80", "20", ""},
                {"Limpiar datos", "100", "24", ""},
                {"Iterar", "1000", "10", ""},
                {"Preparar datos", "50", "Secuencial", "Buscar datos\nEvaluar datos\nLimpiar datos"},
                {"Iterar 3 veces", "100", "Paralela", "Iterar\nIterar\nIterar"}};
        for (String[] c : activities) {
            add(c[0], c[1], c[2], c[3]);
        }
    }

    /**
     * Consult an activity
     *
     * @param name name of the activity
     * @return activity
     */
    public Activity consult(String name) {return activities.get(name.toUpperCase());}

    /**
     * Add a new activity
     *
     * @param name          name of the activity
     * @param timeType      type of the activity
     * @param cost          cost of the activity
     * @param theActivities activities that compose the activity
     */
    public void add(String name, String cost, String timeType, String theActivities) throws ProjectException {
        ProjectException e1 = null;
        if (activities.containsKey(name.toUpperCase())) e1 = new ProjectException(ProjectException.DUPLICATE_ACTIVITY);
        if (cost.isEmpty()) e1 = new ProjectException(ProjectException.COST_EMPTY);
        if (name.isEmpty()) e1 = new ProjectException(ProjectException.DATA_INCOMPLETE);
        if (e1 != null) {
            Log.record(e1);
            throw e1;
        }
        int costi = validateCost(cost);
        Activity na;
        if (theActivities.isEmpty()) {
            if (timeType.isEmpty()) {
                e1 = new ProjectException(ProjectException.TIME_EMPTY);
                Log.record(e1);
                throw e1;
            }
            int time = validateTime(timeType);
            na = new Simple(name, costi, time);
        } else {
            if (!(timeType.equalsIgnoreCase("SECUENCIAL") || timeType.equalsIgnoreCase("PARALELA"))) {
                e1 = new ProjectException(ProjectException.COMPOSED_ERROR);
                Log.record(e1);
                throw e1;
            }
            na = new Composed(name, costi, timeType.toUpperCase().charAt(0) == 'P');
            addActivitiesToComposed(theActivities, na);
        }
        activities.put(name.toUpperCase(), na);
    }

    private void addActivitiesToComposed(String theActivities, Activity na) throws ProjectException {
        String[] aSimples = theActivities.split("\n");
        for (String b : aSimples) {
            if (!activities.containsKey(b.toUpperCase())) {
                ProjectException e = new ProjectException(ProjectException.SUBACTIVITY_ERROR);
                Log.record(e);
                throw e;
            }
            ((Composed) na).add(activities.get(b.toUpperCase()));
        }
    }

    private int validateTime(String timeType) throws ProjectException {
        try {
            int time = Integer.parseInt(timeType);
            if (time < 1 || time > 24) {
                ProjectException e1 = new ProjectException(ProjectException.TIME_ERROR);
                Log.record(e1);
                throw e1;
            }
            return time;
        } catch (NumberFormatException e) {
            ProjectException e1 = new ProjectException(ProjectException.COMPOSED_EMPTY);
            Log.record(e1);
            throw e1;
        }
    }

    private int validateCost(String cost) throws ProjectException {
        try {
            int costi = Integer.parseInt(cost);
            if (costi <= 0) {
                ProjectException e1 = new ProjectException(ProjectException.COST_ERROR);
                Log.record(e1);
                throw e1;
            }
            return costi;
        } catch (NumberFormatException e) {
            ProjectException e1 = new ProjectException(ProjectException.COST_ERROR);
            Log.record(e1);
            throw e1;
        }
    }

    /**
     * Consults the activities that start with a prefix
     *
     * @param prefix prefix of the activities
     * @return activities
     */
    public LinkedList<Activity> select(String prefix) {
        LinkedList<Activity> answers = new LinkedList<>();
        prefix = prefix.toUpperCase();
        for (String s : activities.keySet()) {
            if (activities.get(s).name().toUpperCase().startsWith(prefix.toUpperCase())) {
                answers.add(activities.get(s));
            }
        }
        return answers;
    }

    /**
     * Consult selected activities
     *
     * @param selected activities
     * @return data of the activities
     */
    public String data(LinkedList<Activity> selected) {
        StringBuffer answer = new StringBuffer();
        answer.append(activities.size() + " actividades\n");
        for (Activity p : selected) {
            try {
                answer.append('>' + p.data());
                answer.append("\n");
            } catch (ProjectException e) {
                answer.append("**** " + e.getMessage() + "\n");
            }
        }
        return answer.toString();
    }

    /**
     * Return the data of activities with a prefix
     *
     * @param prefix prefix of the activities
     * @return data of the activities
     */
    public String search(String prefix) throws ProjectException {
        LinkedList<Activity> activity = select(prefix);
        if (activity.isEmpty()) {
            ProjectException e1 = new ProjectException(ProjectException.SEARCH_EMPTY);
            Log.record(e1);
            throw e1;
        }
        return data(activity);
    }

    /**
     * Return the data of all activities
     *
     * @return data of the activities
     */
    public String toString() {return data(new LinkedList<>(activities.values()));}

    /**
     * Consult the number of activities
     *
     * @return number of activities
     */
    public int numberActivitys() {
        return activities.size();
    }
}
