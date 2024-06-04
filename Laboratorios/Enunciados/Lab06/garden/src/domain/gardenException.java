package src.domain;

public class gardenException extends Exception{

    public static final String INVALID_POSITION = "Invalid position";
    public static final String OPENERROR = "Opción Open en construccion";
    public static final String SAVEERROR = "Opción Save en construccion";
    public static final String IMPORTERROR = "Opción Import en construccion";
    public static final String EXPORTERROR = "Opción Export en construccion";

    public gardenException(String message){
        super(message);
    }

}
