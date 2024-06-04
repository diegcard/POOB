package domain; 

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a project that is composed of activities
 * @author POOB  
 * @version ECI 2022
 */

public class Project{
    private HashMap<String,Activity> activities;

    /**
     * Create a Project
     */
    public Project(){
        activities= new HashMap<String,Activity>();
        addSome();
    }

    private void addSome(){
        String [][] activities= {{"Buscar datos","50","50", "" },
                                 {"Evaluar datos","80","80",""},
                                 {"Limpiar datos","100","100",""},
                                 {"Preparar datos", "50", "Secuencial", "Buscar datos\nEvaluar datos\nLimpiar datos"}};
        for (String [] c: activities){
            add(c[0],c[1],c[2],c[3]);
        }
    }


    
    /**
     * Consult a activity
     * @param name
     * @return 
     */
    public Activity consult(String name){
        return activities.get(name.toUpperCase());
    }

    
    /**
     * Add a new activity
     * @param name 
     * @param time
     * @param type
    */
    public void add(String name, String cost, String timeType, String theActivities){ 
        Activity na;
        if (theActivities.equals("")){
           na=new Simple(name,cost.equals("") ? null : Integer.parseInt(cost),timeType.equals("") ? null : Integer.parseInt(timeType));
        }else{ 
            na = new Composed(name,cost.equals("") ? null : Integer.parseInt(cost), timeType.equals("") ? true : timeType.toUpperCase().charAt(0)=='P');
            String [] aSimples= theActivities.split("\n");
            for (String b : aSimples){
                ((Composed)na).add(activities.get(b.toUpperCase()));
            }
        }
        activities.put(name.toUpperCase(),na);
    }


    /**
     * Consults the activities that start with a prefix
     * @param  
     * @return 
     */
    
    public LinkedList<Activity> select(String prefix){
        LinkedList <Activity> answers=null;
        prefix=prefix.toUpperCase();
        for(int i=0;i<activities.size();i++){
            if(activities.get(i).name().toUpperCase().startsWith(prefix.toUpperCase())){
                answers.add(activities.get(i));
            }   
        }
        return answers;
    }


    
    /**
     * Consult selected activities
     * @param selected
     * @return  
     */
    public String data(LinkedList<Activity> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(activities.size()+ " actividades\n");
        for(Activity p : selected) {
            try{
                answer.append('>' + p.data());
                answer.append("\n");
            }catch(ProjectException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
     /**
     * Return the data of activities with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all activities
     * @return  
     */    
    public String toString(){
        return data(new LinkedList<>(activities.values()));
    }
    
    /**
     * Consult the number of activities
     * @return 
     */
    public int numberActivitys(){
        return activities.size();
    }

}
