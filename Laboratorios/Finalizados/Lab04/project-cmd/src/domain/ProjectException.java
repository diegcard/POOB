package domain;


/**
 * This class contains all posible exceptions that can appear during the execution of the app
 * 
 * @author Cardenas-Cardona
 * @version 1.0.0
 */
public class ProjectException extends Exception{

    public static final String TIME_EMPTY = "Debes asignar tiempo a una actividad simple";
    public static final String TIME_ERROR = "Error en el tiempo, verifica limites";
    public static final String COMPOSED_EMPTY = "Las actividades compuestas deben tener subactividades";
    public static final String IMPOSIBLE = "La acción que quieres realizar no se puede calcular";
    public static final String UNKNOWN = "Dicha actividad no existe";
    public static final String COST_EMPTY = "Debes asignar un costo a una actividad simple";
    public static final String COST_ERROR = "Error en el costo, verifica limites";
    
    
    public ProjectException(String message){
        super(message);
    }
}
